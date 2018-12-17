package org.opensrp.service;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.opensrp.domain.*;
import org.opensrp.dto.PatientReferralsDTO;
import org.opensrp.dto.PatientsDTO;
import org.opensrp.dto.ReferralsDTO;
import org.opensrp.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReferralPatientsService {

    private static Logger logger = LoggerFactory.getLogger(ReferralPatientsService.class.toString());

    private HttpClient client;

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private PatientReferralRepository patientReferralRepository;

    @Autowired
    private PatientsAppointmentsRepository patientsAppointmentsRepository;

	@Autowired
	private PatientReferralIndicatorRepository patientReferralIndicatorRepository;

    @Autowired
    private HealthFacilitiesPatientsRepository healthFacilitiesPatientsRepository;

    @Autowired
    private HealthFacilityRepository healthFacilityRepository;


    public ReferralPatientsService() {
        this.client = HttpClientBuilder.create().build();
    }

    public void storeCTCPatients(Patients patient) throws SQLException {
        // create jdbc template to persist the ids
        try {
            if (!this.checkIfClientExists(patient)) {
                patientsRepository.save(patient);
                logger.info("Successfully saved client " + patient.getFirstName());
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    public void storeHealthFacilityRefferal(PatientReferral patientReferral) throws SQLException {
        if (!this.checkIfClientExists(patientReferral)) {
            try {
                patientReferralRepository.save(patientReferral);
            } catch (Exception e) {
                logger.error("", e);
            }

        }
    }

    public List<PatientReferralsDTO> getAllPatientReferrals(){
        return getPatientsReferralsDTO("SELECT * FROM "+ HealthFacilitiesPatients.tbName,null);
    }

    public List<PatientReferralsDTO> getHealthFacilityReferrals(String facilityUUID){

        String[] healthFacilityPatientArg = new String[1];
        healthFacilityPatientArg[0] =  facilityUUID;


        return getPatientsReferralsDTO("SELECT * FROM " + HealthFacilitiesPatients.tbName +
                " INNER JOIN "+HealthFacilities.tbName+" ON "+HealthFacilitiesPatients.tbName+"."+HealthFacilitiesPatients.COL_FACILITY_ID +" = "+HealthFacilities.tbName+"._id " +
                " WHERE " + HealthFacilities.COL_OPENMRS_UIID + "=?",healthFacilityPatientArg);
    }

    public  List<PatientReferralsDTO> getPatientsReferralsDTO(String sql, Object[] arg){

        List<HealthFacilitiesPatients> healthFacilitiesPatients = null;
        try {
            healthFacilitiesPatients = healthFacilitiesPatientsRepository.getHealthFacilityPatients(sql,arg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<PatientReferralsDTO> patientReferralsDTOS = new ArrayList<>();
        for(HealthFacilitiesPatients facilitiesPatients:healthFacilitiesPatients){
            String getPatientsSQL = "SELECT * from " + Patients.tbName+" WHERE "+Patients.COL_PATIENT_ID+ " = "+facilitiesPatients.getPatient().getPatientId();
            try {
                Patients patient = patientsRepository.getPatients(getPatientsSQL,null).get(0);

                PatientReferralsDTO patientReferralsDTO = new PatientReferralsDTO();
                PatientsDTO patientsDTO = PatientsConverter.toPatientsDTO(patient);
                patientsDTO.setCtcNumber(facilitiesPatients.getCtcNumber());
                patientReferralsDTO.setPatientsDTO(patientsDTO);


                String getReferralPatientsSQL = "SELECT * from " + PatientReferral.tbName+" WHERE "+PatientReferral.COL_PATIENT_ID +" =?";
                Object[] args = new Object[]{patient.getPatientId()};

                List<ReferralsDTO> referralsDTOS = PatientsConverter.toPatientReferralDTOsList(patientReferralRepository.getReferrals(getReferralPatientsSQL,args));
                for(ReferralsDTO referralsDTO:referralsDTOS) {

                    Object[] args2 = new Object[]{referralsDTO.getReferralId()};

                    List<PatientReferralIndicators> patientReferralIndicators = patientReferralIndicatorRepository.getPatientReferralIndicators("SELECT * FROM " + PatientReferralIndicators.tbName + " WHERE " + PatientReferralIndicators.COL_REFERRAL_ID + " =?", args2);

                    List<Long> patientReferralIndicatorsIds = new ArrayList<>();
                    for(PatientReferralIndicators referralIndicator:patientReferralIndicators){
                        patientReferralIndicatorsIds.add(referralIndicator.getReferralServiceIndicatorId());
                    }

                    referralsDTO.setServiceIndicatorIds(patientReferralIndicatorsIds);
                }

                patientReferralsDTO.setPatientReferralsList(referralsDTOS);


                Object[] args2 = new Object[]{facilitiesPatients.getHealthFacilityPatientId()};
                String getPatientsAppointmentsSQL = "SELECT * from " + PatientAppointments.tbName+" WHERE "+PatientAppointments.COL_HEALTH_FACILITY_PATIENT_ID +" =?";
                List<PatientAppointments> patientAppointments = patientsAppointmentsRepository.getAppointments(getPatientsAppointmentsSQL,args2);

                patientReferralsDTO.setPatientsAppointmentsDTOS(PatientsConverter.toPatientAppointmentDTOsList(patientAppointments));

                patientReferralsDTOS.add(patientReferralsDTO);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return patientReferralsDTOS;
    }

    public List<Patients> getPatients(String sql, Object[] args){
        try {
            return patientsRepository.getPatients(sql,args);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Boolean checkIfClientExists(Patients patient) throws SQLException {
        try {
            String checkIfExistQuery = "SELECT count(*) from " + Patients.tbName + " WHERE " + Patients.COL_PATIENT_ID + " = ?";
            String[] args = new String[1];
            args[0] = patient.getPatientId()+"";

            int rowCount = patientsRepository.checkIfExists(checkIfExistQuery, args);

            logger.info(
                    "[checkIfClientExists] - Card Number:" + args[0] + " - [Exists] " + (rowCount == 0 ? "false" : "true"));

            return rowCount >= 1;
        } catch (Exception e) {
            logger.error("", e);
            return null;
        }
    }

    public Boolean checkIfClientExists(PatientReferral patientReferral) throws SQLException {
        try {
            String checkIfExistQuery = "SELECT count(*) from " + PatientReferral.tbName + " WHERE  "+PatientReferral.COL_REFERRAL_ID+" = ?";
            Object[] args = new Object[1];
            args[0] = patientReferral.getId();
            int rowCount = patientsRepository.checkIfExists(checkIfExistQuery, args);

            logger.info(
                    "[checkIfClientExists] - Referral ID:" + args[0] + " - [Exists] " + (rowCount == 0 ? "false" : "true"));

            return rowCount >= 1;
        } catch (Exception e) {
            logger.error("", e);
            return null;
        }
    }

    public String delete_last_char_java(String string) {

        String phrase = "level up lunch";

        String rephrase = null;
        if (phrase != null && phrase.length() > 1) {
            rephrase = phrase.substring(0, phrase.length() - 1);
        }

        return rephrase;
    }

    public long savePatient(Patients patient, String healthFacilityCode, String ctcNumber) {
        String query = "SELECT * FROM " + Patients.tbName + " WHERE " +
                Patients.COL_PATIENT_FIRST_NAME + " = ?     AND " +
                Patients.COL_PATIENT_MIDDLE_NAME + " = ?    AND " +
                Patients.COL_PATIENT_SURNAME + " = ?        AND " +
                Patients.COL_PHONE_NUMBER + " = ?";
        Object[] params = new Object[]{
                patient.getFirstName(),
                patient.getMiddleName(),
                patient.getSurname(),
                patient.getPhoneNumber()};
        List<Patients> patientsResults = null;
        try {
            patientsResults = patientsRepository.getPatients(query, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Coze = number of patients found = " + patientsResults.size());
        long id;
        if (patientsResults.size() > 0) {
            System.out.println("Coze = using the received patients ");
            id = patientsResults.get(0).getPatientId();
        } else {
            System.out.println("Coze = saving patient Data ");
            try {
                id = patientsRepository.save(patient);
            } catch (Exception e) {
                e.printStackTrace();
                id = -1;
            }
        }

        //Obtaining health facilityId from tbl_facilities
        String healthFacilitySql = "SELECT * FROM " + HealthFacilities.tbName + " WHERE " +
                HealthFacilities.COL_FACILITY_CTC_CODE + " = ? OR " + HealthFacilities.COL_OPENMRS_UIID + " = ?";
        Object[] healthFacilityParams = new Object[]{
                healthFacilityCode,healthFacilityCode};

        System.out.println("Coze facility ctc code = " + healthFacilityCode);
        Long healthFacilityId = (long) 0;
        List<HealthFacilities> healthFacilities = null;
        try {
            healthFacilities = healthFacilityRepository.getHealthFacility(healthFacilitySql, healthFacilityParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (healthFacilities.size() > 0) {
            healthFacilityId = healthFacilities.get(0).getId();
        }

        HealthFacilitiesPatients healthFacilitiesPatients = new HealthFacilitiesPatients();

        Patients patients = new Patients();
        patients.setPatientId(id);

        healthFacilitiesPatients.setPatient(patients);
        healthFacilitiesPatients.setCtcNumber(ctcNumber);
        healthFacilitiesPatients.setFacilityId(healthFacilityId);


        String healthFacilityPatientsquery = "SELECT * FROM " + HealthFacilitiesPatients.tbName + " WHERE " +
                HealthFacilitiesPatients.COL_PATIENT_ID + " = ?    AND " +
                HealthFacilitiesPatients.COL_FACILITY_ID + " = ?";

        Object[] healthFacilityPatientsparams = new Object[]{
                healthFacilitiesPatients.getPatient().getPatientId(),
                healthFacilitiesPatients.getFacilityId()};

        List<HealthFacilitiesPatients> healthFacilitiesPatientsResults = null;
        try {
            healthFacilitiesPatientsResults = healthFacilitiesPatientsRepository.getHealthFacilityPatients(healthFacilityPatientsquery, healthFacilityPatientsparams);
        } catch (Exception e) {
            e.printStackTrace();
        }


        long healthfacilityPatientId = -1;
        if (healthFacilitiesPatientsResults.size() > 0) {
            healthfacilityPatientId = healthFacilitiesPatientsResults.get(0).getHealthFacilityPatientId();
        } else {
            try {
                healthfacilityPatientId = healthFacilitiesPatientsRepository.save(healthFacilitiesPatients);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return healthfacilityPatientId;
    }
}
