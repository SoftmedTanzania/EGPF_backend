package org.opensrp.service;

import org.opensrp.domain.*;
import org.opensrp.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.*;

import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;

public class PatientsConverter {
    private static Logger logger = LoggerFactory.getLogger(PatientsConverter.class.toString());


    public static Patients toPatients(PatientsDTO patientsDTO) {
        try {
            Patients patients = new Patients();


            patients.setPatientId(patientsDTO.getPatientId());
            patients.setFirstName(patientsDTO.getFirstName());
            patients.setMiddleName(patientsDTO.getMiddleName());
            patients.setSurname(patientsDTO.getSurname());
            patients.setPhoneNumber(patientsDTO.getPhoneNumber());

            Date dob = new Date();
            dob.setTime(patientsDTO.getDateOfBirth());

            patients.setDateOfBirth(dob);
            patients.setGender(patientsDTO.getGender());
            patients.setWard(patientsDTO.getWard());
            patients.setVillage(patientsDTO.getVillage());
            patients.setHamlet(patientsDTO.getHamlet());
            patients.setCareTakerName(patientsDTO.getCareTakerName());
            patients.setCareTakerPhoneNumber(patientsDTO.getPhoneNumber());
            patients.setCareTakerRelationship(patientsDTO.getCareTakerRelationship());
            patients.setCommunityBasedHivService(patientsDTO.getCommunityBasedHivService());

            try {
                Date deathDate = new Date();
                deathDate.setTime(patientsDTO.getDateOfDeath());
                patients.setDateOfDeath(deathDate);
            }catch (Exception e){
                e.printStackTrace();
            }

            patients.setHivStatus(patientsDTO.isHivStatus());
            patients.setCreatedAt(Calendar.getInstance().getTime());
            patients.setUpdatedAt(Calendar.getInstance().getTime());

            return patients;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting CTCPatientDTO :{0}, failed with error: {1}.", patientsDTO, e));
            throw e;
        }
    }



    public static Patients toPatients(CTCPatientsDTO patientsDTO) {
        try {
            Patients patients = new Patients();
            patients.setFirstName(patientsDTO.getFirstName());
	        patients.setMiddleName(patientsDTO.getMiddleName());
            patients.setSurname(patientsDTO.getSurname());
            patients.setPhoneNumber(patientsDTO.getContact());
            Date dob = new Date();
            dob.setTime(patientsDTO.getDateOfBirth());
            patients.setDateOfBirth(dob);
            patients.setGender(patientsDTO.getGender());

            try {
                Date deathDate = new Date();
                deathDate.setTime(patientsDTO.getDateOfDeath());
                patients.setDateOfDeath(deathDate);
            }catch (Exception e){
                e.printStackTrace();
            }

            patients.setCareTakerName(patientsDTO.getCareTakerName());
            patients.setCareTakerPhoneNumber(patientsDTO.getCareTakerPhoneNumber());
            patients.setCareTakerRelationship(patientsDTO.getCareTakerRelationship());

            patients.setCreatedAt(Calendar.getInstance().getTime());
            patients.setUpdatedAt(Calendar.getInstance().getTime());
            patients.setHivStatus(patientsDTO.isHivStatus());

            return patients;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting CTCPatientDTO :{0}, failed with error: {1}.", patientsDTO, e));
            throw e;
        }
    }


    public static Patients toPatients(TBPatientMobileClientDTO patientsDTO) {
        try {
            Patients patients = new Patients();


            patients.setFirstName(patientsDTO.getFirstName());
            patients.setSurname(patientsDTO.getSurname());
            patients.setMiddleName(patientsDTO.getMiddleName());
            patients.setPhoneNumber(patientsDTO.getPhoneNumber());
            patients.setHamlet(patientsDTO.getHamlet());
            patients.setVillage(patientsDTO.getVillage());
            patients.setHamlet(patientsDTO.getHamlet());
            patients.setPhoneNumber(patientsDTO.getPhoneNumber());
            Date dob = new Date();
            dob.setTime(patientsDTO.getDateOfBirth());
            patients.setDateOfBirth(dob);
            patients.setGender(patientsDTO.getGender());
            patients.setHivStatus(patientsDTO.isHivStatus());
            patients.setHivStatus(patientsDTO.isHivStatus());

            Date deathDate = new Date();
            deathDate.setTime(patientsDTO.getDateOfDeath());

            patients.setDateOfDeath(deathDate);
            patients.setCreatedAt(Calendar.getInstance().getTime());
            patients.setUpdatedAt(Calendar.getInstance().getTime());

            return patients;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting TBPatientMobileClientDTO :{0}, failed with error: {1}.", patientsDTO, e));
            throw e;
        }
    }

    public static TBPatient toTBPatients(TBPatientMobileClientDTO patientsDTO) {
        try {
            TBPatient tbPatient = new TBPatient();
            tbPatient.setOutcomeDetails(patientsDTO.getOutcomeDetails());
            tbPatient.setOutcome(patientsDTO.getOutcome());
            tbPatient.setTbPatientId(patientsDTO.getPatientId());

	        Date outcomeDate = new Date();
	        outcomeDate.setTime(patientsDTO.getOutcomeDate());

            tbPatient.setOutcomeDate(outcomeDate);
            tbPatient.setOtherTestsDetails(patientsDTO.getOtherTestsDetails());
            tbPatient.setTestType(patientsDTO.getTestType());
            tbPatient.setMakohozi(patientsDTO.getMakohozi());
            tbPatient.setWeight(patientsDTO.getWeight());
            tbPatient.setXray(patientsDTO.getXray());
            tbPatient.setVeo(patientsDTO.getVeo());
            tbPatient.setReferralType(patientsDTO.getReferralType());
            tbPatient.setTreatment_type(patientsDTO.getTreatment_type());
            tbPatient.setTransferType(patientsDTO.getTransferType());
            tbPatient.setPatientType(patientsDTO.getPatientType());
            tbPatient.setPregnant(patientsDTO.isPregnant());

            return tbPatient;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting TBPatientMobileClientDTO :{0}, failed with error: {1}.", patientsDTO, e));
            throw e;
        }
    }


    public static List<PatientAppointments> toPatientsAppointments(CTCPatientsDTO patientsDTO) {
        try {

            List<PatientAppointments> patientAppointments = new ArrayList<>();
            List<CTCPatientsAppointmesDTO> appointments = patientsDTO.getPatientAppointments();

            if (appointments!=null) {
	            for (CTCPatientsAppointmesDTO appointment : appointments) {
		            PatientAppointments patientAppointment = new PatientAppointments();
		            Date appointDate = new Date();
		            appointDate.setTime(appointment.getDateOfAppointment());

		            patientAppointment.setAppointmentDate(appointDate);
		            patientAppointment.setIsCancelled(appointment.isCancelled());

		            patientAppointment.setStatus("0");
		            patientAppointment.setAppointmentType(1);
		            patientAppointments.add(patientAppointment);
	            }
            }else{
                System.out.println("coze patients appointment is empty");
            }

            return patientAppointments;

        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting CTCPatientDTO :{0}, failed with error: {1}.", patientsDTO, e));
            throw e;
        }
    }


    public static PatientsDTO toPatientsDTO(Patients patients) {
        try {
            PatientsDTO patientsDTO = new PatientsDTO();

            patientsDTO.setPatientId(patients.getPatientId());
            patientsDTO.setFirstName(patients.getFirstName());
            patientsDTO.setSurname(patients.getSurname());
            patientsDTO.setMiddleName(patients.getMiddleName());
            patientsDTO.setPhoneNumber(patients.getPhoneNumber());

            patientsDTO.setDateOfBirth(patients.getDateOfBirth().getTime());

            patientsDTO.setGender(patients.getGender());
            patientsDTO.setWard(patients.getWard());
            patientsDTO.setVillage(patients.getVillage());
            patientsDTO.setHamlet(patients.getHamlet());

            patientsDTO.setCareTakerName(patients.getCareTakerName());

            patientsDTO.setCareTakerPhoneNumber(patients.getCareTakerPhoneNumber());
            patientsDTO.setCareTakerRelationship(patients.getCareTakerRelationship());

            try {
	            patientsDTO.setDateOfDeath(patients.getDateOfDeath().getTime());
            }catch (Exception e){
	            patientsDTO.setDateOfDeath((long)0);
            	e.printStackTrace();
            }

            try {
                patientsDTO.setUpdatedAt(patients.getUpdatedAt().getTime());
            }catch (Exception e){
                e.printStackTrace();
            }
            patientsDTO.setHivStatus(patients.isHivStatus());

            return patientsDTO;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting Patient :{0}, failed with error: {1}.", patients, e));
            throw e;
        }
    }

    public static TBPatientDTO toTbPatientDTO(TBPatient patient) {
        try {
            TBPatientDTO tbPatientDTO = new TBPatientDTO();

            tbPatientDTO.setHealthFacilityPatientId(patient.getHealthFacilitiesPatients().getHealthFacilityPatientId());
            tbPatientDTO.setMakohozi(patient.getMakohozi());
            tbPatientDTO.setOtherTestsDetails(patient.getOtherTestsDetails());
            tbPatientDTO.setTestType(patient.getTestType());
            tbPatientDTO.setOutcome(patient.getOutcome());
            tbPatientDTO.setOutcomeDate(patient.getOutcomeDate().getTime());
            tbPatientDTO.setOutcomeDetails(patient.getOutcomeDetails());
            tbPatientDTO.setPatientType(patient.getPatientType());
            tbPatientDTO.setPregnant(patient.isPregnant());
            tbPatientDTO.setReferralType(patient.getReferralType());
            tbPatientDTO.setTbPatientId(patient.getTbPatientId());
            tbPatientDTO.setTransferType(patient.getTransferType());
            tbPatientDTO.setTreatment_type(patient.getTreatment_type());
            tbPatientDTO.setVeo(patient.getVeo());
            tbPatientDTO.setWeight(patient.getWeight());
            tbPatientDTO.setXray(patient.getXray());

            return tbPatientDTO;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting TBPatientDTO :{0}, failed with error: {1}.", patient, e));
            throw e;
        }
    }


    public static List<PatientReferral> toPatientReferralsList(List<ReferralsDTO> referralsDTOs) {
        try {

            List<PatientReferral> patientReferrals = new ArrayList<>();
            for(ReferralsDTO referralsDTO:referralsDTOs){
                patientReferrals.add(toPatientReferral(referralsDTO));
            }

            return patientReferrals;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting List<ReferralsDTO> :{0}, failed with error: {1}.", referralsDTOs, e));
            throw e;
        }
    }



    public static List<ReferralsDTO> toPatientReferralDTOsList(List<PatientReferral> referrals) {
        try {

            List<ReferralsDTO> referralsDTOS = new ArrayList<>();
            for(PatientReferral referral:referrals){
                referralsDTOS.add(toPatientDTO(referral));
            }

            return referralsDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageFormat.format("Converting List<PatientReferral> :{0}, failed with error: {1}.", referrals, e));
            throw e;
        }
    }


    public static PatientReferral toPatientReferral(ReferralsDTO referralsDTO) {
        try {
            PatientReferral referral = new PatientReferral();

	        Patients patient  = new Patients();
	        patient.setPatientId(referralsDTO.getPatientId());
	        referral.setId(referralsDTO.getReferralId());
            referral.setPatient(patient);
            referral.setCommunityBasedHivService(referralsDTO.getCommunityBasedHivService());
            referral.setReferralReason(referralsDTO.getReferralReason());
            referral.setServiceId(referralsDTO.getServiceId());
            referral.setCtcNumber(referralsDTO.getServiceProviderGroup());
            referral.setReferralUUID(referralsDTO.getReferralUUID());
            referral.setReferralType(referralsDTO.getReferralType());

            referral.setReferralStatus(referralsDTO.getReferralStatus());
            referral.setServiceProviderUIID(referralsDTO.getServiceProviderUIID());
            referral.setServiceProviderGroup(referralsDTO.getServiceProviderGroup());
            referral.setVillageLeader(referralsDTO.getVillageLeader());
            referral.setTestResults(referralsDTO.getTestResults());
            referral.setLabTest(referralsDTO.getLabTest());


            Date referralDate = new Date();
            referralDate.setTime(referralsDTO.getReferralDate());
            referral.setReferralDate(referralDate);

            try {
                Date appointmentDate = new Date();
                appointmentDate.setTime(referralsDTO.getAppointmentDate());
                referral.setAppointmentDate(appointmentDate);
            }catch (Exception e){
                e.printStackTrace();

                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(referralsDTO.getReferralDate());
                c.add(Calendar.DATE,3);
                referral.setAppointmentDate(c.getTime());
            }

            referral.setFacilityId(referralsDTO.getFacilityId());

            try {
                referral.setEmergency(referralsDTO.isEmergency());
            }catch (Exception e){
                e.printStackTrace();
                referral.setEmergency(false);
            }

            referral.setReferralSource(referralsDTO.getReferralSource());
            referral.setServiceGivenToPatient(referralsDTO.getServiceGivenToPatient());
            referral.setFromFacilityId(referralsDTO.getFromFacilityId());
            referral.setOtherClinicalInformation(referralsDTO.getOtherClinicalInformation());
            referral.setOtherNotes(referralsDTO.getOtherNotes());

	        referral.setInstanceId(referralsDTO.getIntanceId());

            referral.setCreatedAt(Calendar.getInstance().getTime());
            referral.setUpdatedAt(Calendar.getInstance().getTime());

            return referral;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting CTCPatientDTO :{0}, failed with error: {1}.", referralsDTO, e));
            throw e;
        }
    }


    public static ReferralsDTO toPatientDTO(PatientReferral referral) {
        try {
            ReferralsDTO referralsDTO = new ReferralsDTO();

            referralsDTO.setPatientId(referral.getPatient().getPatientId());
            referralsDTO.setCommunityBasedHivService(referral.getCommunityBasedHivService());
            referralsDTO.setReferralReason(referral.getReferralReason());
            referralsDTO.setServiceId(referral.getServiceId());
            referralsDTO.setReferralId(referral.getId());
            referralsDTO.setReferralStatus(referral.getReferralStatus());
            referralsDTO.setReferralUUID(referral.getReferralUUID());
            referralsDTO.setReferralSource(referral.getReferralSource());
            referralsDTO.setReferralType((int)referral.getReferralType());
            referralsDTO.setOtherNotes(referral.getOtherNotes());
            referralsDTO.setServiceGivenToPatient(referral.getServiceGivenToPatient());
            referralsDTO.setTestResults(referral.isTestResults());
            referralsDTO.setLabTest(referral.getLabTest());
            referralsDTO.setFromFacilityId(referral.getFromFacilityId());
            referralsDTO.setOtherClinicalInformation(referral.getOtherClinicalInformation());

            referralsDTO.setIntanceId(referral.getInstanceId());
            try {
                referralsDTO.setUpdatedAt(referral.getUpdatedAt().getTime());
            }catch (Exception e){
                e.printStackTrace();
            }



            try {

                referralsDTO.setAppointmentDate(referral.getAppointmentDate().getTime());
            }catch (Exception e){
                e.printStackTrace();

                Calendar c = Calendar.getInstance();
                c.setTime(referral.getReferralDate());
                c.add(Calendar.DATE,3);
                referralsDTO.setAppointmentDate(c.getTime().getTime());
            }

            try {
                referralsDTO.setEmergency(referral.isEmergency());
            }catch (Exception e){
                e.printStackTrace();
                referralsDTO.setEmergency(false);
            }


            referralsDTO.setServiceProviderUIID(referral.getServiceProviderUIID());
            referralsDTO.setServiceProviderGroup(referral.getServiceProviderGroup());
            referralsDTO.setVillageLeader(referral.getVillageLeader());
            referralsDTO.setReferralDate(referral.getReferralDate().getTime());
            referralsDTO.setFacilityId(referral.getFacilityId());

            return referralsDTO;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting ReferralsDTO :{0}, failed with error: {1}.", referral, e));
            e.printStackTrace();
            throw e;
        }
    }


    public static TBEncounter toTBEncounter(TBEncounterDTO tbEncounterDTO) {
        try {
           TBEncounter encounter = new TBEncounter();
           encounter.setTbPatientId(tbEncounterDTO.getTbPatientId());
           Date medicationDate = new Date();
           medicationDate.setTime(tbEncounterDTO.getMedicationDate());

           encounter.setLocalID(tbEncounterDTO.getLocalID());
           encounter.setWeight(tbEncounterDTO.getWeight());
           encounter.setMedicationDate(medicationDate);
           encounter.setMedicationStatus(tbEncounterDTO.isMedicationStatus());

           encounter.setEncounterYear(tbEncounterDTO.getEncounterYear());

           Date scheduledDate = new Date();
           scheduledDate.setTime(tbEncounterDTO.getScheduledDate());
           encounter.setScheduledDate(scheduledDate);
           encounter.setHasFinishedPreviousMonthMedication(tbEncounterDTO.isHasFinishedPreviousMonthMedication());
           encounter.setEncounterMonth(tbEncounterDTO.getEncounterMonth());
           encounter.setAppointmentId(tbEncounterDTO.getAppointmentId());
           encounter.setMakohozi(tbEncounterDTO.getMakohozi());
           encounter.setUpdatedAt(Calendar.getInstance().getTime());

            return encounter;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting TBEncounterDTO :{0}, failed with error: {1}.", tbEncounterDTO, e));
            throw e;
        }
    }


	public static List<PatientsAppointmentsDTO> toPatientAppointmentDTOsList(List<PatientAppointments> patientAppointments) {
		try {

			List<PatientsAppointmentsDTO> patientsAppointmentsDTOS = new ArrayList<>();
			for(PatientAppointments appointments:patientAppointments){
				patientsAppointmentsDTOS.add(toPatientAppointmentsDTO(appointments));
			}

			return patientsAppointmentsDTOS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Converting  List<PatientsAppointmentsDTO> :{0}, failed with error: {1}.", patientAppointments, e));
			throw e;
		}
	}

	public static PatientsAppointmentsDTO toPatientAppointmentsDTO(PatientAppointments patientAppointments) {
		try {
			PatientsAppointmentsDTO patientsAppointmentsDTO = new PatientsAppointmentsDTO();

			patientsAppointmentsDTO.setAppointment_id(patientAppointments.getAppointment_id());
			patientsAppointmentsDTO.setAppointmentDate(patientAppointments.getAppointmentDate().getTime());
			patientsAppointmentsDTO.setIsCancelled(patientAppointments.getIsCancelled());
			patientsAppointmentsDTO.setHealthFacilityPatientId(patientAppointments.getHealthFacilitiesPatients().getHealthFacilityPatientId());
			patientsAppointmentsDTO.setStatus(patientAppointments.getStatus());
			patientsAppointmentsDTO.setAppointmentType(patientAppointments.getAppointmentType());

			return patientsAppointmentsDTO;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Converting PatientsAppointmentsDTO :{0}, failed with error: {1}.", patientAppointments, e));
			throw e;
		}
	}

    public static List<TBEncounterDTO> toTbPatientEncounterDTOsList(List<TBEncounter> tbEncounters) {
        try {

            List<TBEncounterDTO> tbEncounterDTOS = new ArrayList<>();
            for(TBEncounter tbEncounter :tbEncounters){
                tbEncounterDTOS.add(toTbEncounterDTO(tbEncounter));
            }

            return tbEncounterDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageFormat.format("Converting   List<TBEncounterDTO> :{0}, failed with error: {1}.", tbEncounters, e));
            throw e;
        }
    }

	public static TBEncounterDTO toTbEncounterDTO(TBEncounter tbEncounter) {
		try {
            TBEncounterDTO tbEncounterDTO = new TBEncounterDTO();

            tbEncounterDTO.setLocalID(tbEncounter.getLocalID());
            tbEncounterDTO.setAppointmentId(tbEncounter.getAppointmentId());
            tbEncounterDTO.setEncounterMonth(tbEncounter.getEncounterMonth());
            tbEncounterDTO.setEncounterYear(tbEncounter.getEncounterYear());
            tbEncounterDTO.setHasFinishedPreviousMonthMedication(tbEncounter.isHasFinishedPreviousMonthMedication());
            tbEncounterDTO.setId(tbEncounter.getId());
            tbEncounterDTO.setMakohozi(tbEncounter.getMakohozi());
            tbEncounterDTO.setMedicationDate(tbEncounter.getMedicationDate().getTime());
            tbEncounterDTO.setMedicationStatus(tbEncounter.isMedicationStatus());
            tbEncounterDTO.setTbPatientId(tbEncounter.getTbPatientId());
            tbEncounterDTO.setWeight(tbEncounter.getWeight());
            tbEncounterDTO.setScheduledDate(tbEncounter.getScheduledDate().getTime());

			return tbEncounterDTO;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Converting TBEncounterDTO :{0}, failed with error: {1}.", tbEncounter, e));
			throw e;
		}
	}


}
