package org.opensrp.web.controller;

import static org.opensrp.web.HttpHeaderFactory.allowOrigin;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.nio.charset.Charset;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opensrp.api.domain.Time;
import org.opensrp.api.domain.User;
import org.opensrp.api.util.LocationTree;
import org.opensrp.common.domain.UserDetail;
import org.opensrp.connector.openmrs.service.OpenmrsLocationService;
import org.opensrp.connector.openmrs.service.OpenmrsUserService;
import org.opensrp.domain.HealthFacilities;
import org.opensrp.dto.ReferralsDTO;
import org.opensrp.repository.HealthFacilityRepository;
import org.opensrp.web.security.DrishtiAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.jdbc.StringUtils;

@Controller
public class UserController {
    private String opensrpSiteUrl;
    private DrishtiAuthenticationProvider opensrpAuthenticationProvider;
	private OpenmrsLocationService openmrsLocationService;
	private OpenmrsUserService openmrsUserService;

	private HealthFacilityRepository facilityRepository;
	
    @Autowired
    public UserController(OpenmrsLocationService openmrsLocationService, OpenmrsUserService openmrsUserService, 
            DrishtiAuthenticationProvider opensrpAuthenticationProvider, HealthFacilityRepository facilityRepository) {
		this.openmrsLocationService = openmrsLocationService;
		this.openmrsUserService = openmrsUserService;
        this.opensrpAuthenticationProvider = opensrpAuthenticationProvider;
        this.facilityRepository = facilityRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/authenticate-user")
    public ResponseEntity<HttpStatus> authenticateUser() {
        return new ResponseEntity<>(null, allowOrigin(opensrpSiteUrl), OK);
    }

    public Authentication getAuthenticationAdvisor(HttpServletRequest request) {
    	final String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Basic")) {
            // Authorization: Basic base64credentials
            String base64Credentials = authorization.substring("Basic".length()).trim();
            String credentials = new String(Base64.decode(base64Credentials.getBytes()), Charset.forName("UTF-8"));
            // credentials = username:password
            final String[] values = credentials.split(":",2);
    		
            return new UsernamePasswordAuthenticationToken(values[0], values[1]);
        }
		return null;	
	}
    
    public DrishtiAuthenticationProvider getAuthenticationProvider() {
		return opensrpAuthenticationProvider;
	}
    
    public User currentUser(HttpServletRequest request) {
    	Authentication a = getAuthenticationAdvisor(request);
    	return getAuthenticationProvider().getDrishtiUser(a, a.getName());
    }

    public Time getServerTime() {
    	return new Time(Calendar.getInstance().getTime(), TimeZone.getDefault());
	}

    @RequestMapping(method = RequestMethod.GET, value = "/user-details")
    public ResponseEntity<UserDetail> userDetail(@RequestParam("anm-id") String anmIdentifier, HttpServletRequest request) {
    	Authentication a = getAuthenticationAdvisor(request);
        User user = opensrpAuthenticationProvider.getDrishtiUser(a, anmIdentifier);
        return new ResponseEntity<>(new UserDetail(user.getUsername(), user.getRoles()), allowOrigin(opensrpSiteUrl), OK);
    }

	@RequestMapping("/security/authenticate")
	@ResponseBody
	public ResponseEntity<String> authenticate(HttpServletRequest request) throws JSONException {
        User u = currentUser(request);
        String lid = "";
        JSONObject tm = null;
        try{
        	tm = openmrsUserService.getTeamMember(u.getAttribute("_PERSON_UUID").toString());
        	JSONArray locs = tm.getJSONArray("locations");
        	for (int i = 0; i < locs.length(); i++) {
				lid += locs.getJSONObject(i).getString("uuid")+";;";
			}
        }
        catch(Exception e){
        	System.out.println("USER Location info not mapped in team management module. Now trying Person Attribute");;
        }
        if(StringUtils.isEmptyOrWhitespaceOnly(lid)){
	        lid = (String) u.getAttribute("Location");
	        if(StringUtils.isEmptyOrWhitespaceOnly(lid)){
	            String lids = (String) u.getAttribute("Locations");
	            
	            if(lids == null){
	            	throw new RuntimeException("User not mapped on any location. Make sure that user have a person attribute Location or Locations with uuid(s) of valid OpenMRS Location(s) separated by ;;");
	            }
	            
	            lid = lids;
	        }
        }
		LocationTree l = openmrsLocationService.getLocationTreeOf(lid.split(";;"));
		Map<String, Object> map = new HashMap<>();
		map.put("user", u);
		try{
			Map<String, Object> tmap = new Gson().fromJson(tm.toString(), new TypeToken<HashMap<String, Object>>() {}.getType());
			map.put("team", tmap);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		map.put("locations", l);
		Time t = getServerTime();
		map.put("time", t);
        return new ResponseEntity<>(new Gson().toJson(map), allowOrigin(opensrpSiteUrl), OK);
	}
	
	@RequestMapping("/security/configuration")
	@ResponseBody
	public ResponseEntity<String> configuration() throws JSONException {
		Map<String, Object> map = new HashMap<>();
		map.put("serverDatetime", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        return new ResponseEntity<>(new Gson().toJson(map), allowOrigin(opensrpSiteUrl), OK);
	}


	@RequestMapping(headers = {"Accept=application/json"}, method = POST, value = "/get-team-members-by-facility-uuid")
	public ResponseEntity<String> getTeamMembers(@RequestBody String jsonData) {
		List<String> facilityHFRCodes = new Gson().fromJson(jsonData, new TypeToken<List<String>>() {}.getType());

		String facilitiesHFRCodes = "";
		for(String facilityHFR :  facilityHFRCodes){
			facilitiesHFRCodes+="'"+facilityHFR+"',";
		}

		if ( facilitiesHFRCodes.length() > 0 && facilitiesHFRCodes.charAt(facilitiesHFRCodes.length() - 1) == ',') {
			facilitiesHFRCodes = facilitiesHFRCodes.substring(0, facilitiesHFRCodes.length() - 1);
		}

		System.out.println("FACILITY-HFR : "+facilitiesHFRCodes);


		List<HealthFacilities> healthFacilities = null;
		try {
			String sql = "SELECT * FROM "+ HealthFacilities.tbName+" WHERE "+HealthFacilities.COL_HFR_CODE+ " IN ("+facilitiesHFRCodes+")";
			healthFacilities = facilityRepository.getHealthFacility(sql,null);

			System.out.println("FACILITY-HFR-SQL : "+sql);

		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> healthFacilitiesOpenMRSUUIDS = new ArrayList<>();
		for(HealthFacilities healthFacility:healthFacilities){
			healthFacilitiesOpenMRSUUIDS.add(healthFacility.getOpenMRSUIID());
			System.out.println("FACILITY-UUID : "+healthFacility.getOpenMRSUIID());
		}

		System.out.println("FACILITY-UUID-LIST : "+new Gson().toJson(healthFacilitiesOpenMRSUUIDS));
		JSONArray jsonArray = null;
		try {
			jsonArray = openmrsUserService.getTeamMembersByFacilityId(healthFacilitiesOpenMRSUUIDS);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(jsonArray.toString(), OK);
	}
}
