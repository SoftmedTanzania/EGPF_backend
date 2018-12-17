package org.opensrp.web.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.opensrp.common.AllConstants;
import org.opensrp.domain.HealthFacilities;
import org.opensrp.dto.HealthFacilitiesDTO;
import org.opensrp.dto.PatientReferralsDTO;
import org.opensrp.repository.HealthFacilityRepository;
import org.opensrp.scheduler.SystemEvent;
import org.opensrp.scheduler.TaskSchedulerService;
import org.opensrp.service.HealthFacilitiesConverter;
import org.opensrp.service.HealthFacilitiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static ch.lambdaj.collection.LambdaCollections.with;
import static java.text.MessageFormat.format;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class HealthFacilitiesController {
    private static Logger logger = LoggerFactory.getLogger(HealthFacilitiesController.class.toString());
    private HealthFacilitiesService healthFacilitiesService;
    private HealthFacilityRepository facilityRepository;
    private TaskSchedulerService scheduler;

    @Autowired
    public HealthFacilitiesController(HealthFacilitiesService healthFacilitiesService, HealthFacilityRepository facilityRepository, TaskSchedulerService scheduler) {
        this.healthFacilitiesService = healthFacilitiesService;
        this.facilityRepository = facilityRepository;
        this.scheduler = scheduler;
    }

    @RequestMapping(headers = {"Accept=application/json"}, method = POST, value = "/save-multiple-health-facilities")
    public ResponseEntity<HttpStatus> saveHealthFacilities(@RequestBody String json) {
        try {
            System.out.println("Coze:save health facility");
            List<HealthFacilitiesDTO> healthFacilitiesDTOS = new Gson().fromJson(json, new TypeToken<List<HealthFacilitiesDTO>>() {}.getType());
            if (healthFacilitiesDTOS.isEmpty()) {
                return new ResponseEntity<>(BAD_REQUEST);
            }

            scheduler.notifyEvent(new SystemEvent<>(AllConstants.OpenSRPEvent.HEALTH_FACILITY_SUBMISSION, healthFacilitiesDTOS));

            List<HealthFacilities> healthFacilities =  HealthFacilitiesConverter.toHealthFacilities(healthFacilitiesDTOS);

            for (HealthFacilities healthFacility : healthFacilities) {
                healthFacilitiesService.storeHealthFacilities(healthFacility);
            }

            logger.debug(format("Saved Health Facility to queue.\nSubmissions: {0}", healthFacilitiesDTOS));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(format("Health Facility processing failed with exception {0}.\nSubmissions: {1}", e, json));
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(CREATED);
    }

    @RequestMapping(headers = {"Accept=application/json"}, method = POST, value = "/save-health-facilities")
    public ResponseEntity<HttpStatus> saveHealthFacility(@RequestBody String json) {
        try {
            System.out.println("Coze:save health facility");
             HealthFacilitiesDTO healthFacilitiesDTOS = new Gson().fromJson(json, new TypeToken<List<HealthFacilitiesDTO>>() {}.getType());


            scheduler.notifyEvent(new SystemEvent<>(AllConstants.OpenSRPEvent.HEALTH_FACILITY_SUBMISSION, healthFacilitiesDTOS));

            HealthFacilities healthFacility =  HealthFacilitiesConverter.toHealthFacilities(healthFacilitiesDTOS);

            healthFacilitiesService.storeHealthFacilities(healthFacility);

            logger.debug(format("Saved Health Facility to queue.\nSubmissions: {0}", healthFacilitiesDTOS));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(format("Health Facility processing failed with exception {0}.\nSubmissions: {1}", e, json));
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(CREATED);
    }

    @RequestMapping(method = GET, value = "/get-health-facilities")
    @ResponseBody
    private List<HealthFacilities> getHealthFacilities() {
        List<HealthFacilities> healthFacilities = null;
        try {
            healthFacilities = facilityRepository.getHealthFacility("Select * from "+ HealthFacilities.tbName,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return healthFacilities;
    }


    @RequestMapping(method = GET, value = "/get-district-health-facilities/{facilityUUID}")
    @ResponseBody
    private List<HealthFacilities> getDistrictHealthFacilities(@PathVariable("facilityUUID") String facilityUuid) {
        List<HealthFacilities> healthFacilities = null;
        try {
            healthFacilities = facilityRepository.getHealthFacility("SELECT * FROM "+HealthFacilities.tbName+" WHERE "+
                    HealthFacilities.COL_PARENT_OPENMRS_UIID+" IN (" +
                        "SELECT "+HealthFacilities.COL_PARENT_OPENMRS_UIID+" FROM "+HealthFacilities.tbName+
                        " WHERE "+HealthFacilities.COL_OPENMRS_UIID+"='"+facilityUuid+"')",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return healthFacilities;
    }
}
