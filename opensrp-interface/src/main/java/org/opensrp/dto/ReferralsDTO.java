package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferralsDTO {

    @JsonProperty
    private Long referralId;

    @JsonProperty
    private Long patientId;

    @JsonProperty
    private String communityBasedHivService;

    @JsonProperty
    private String referralReason;

    @JsonProperty
    private int serviceId;

    @JsonProperty
    private String ctcNumber;

    @JsonProperty
    private String referralUUID;

    @JsonProperty
    private String serviceProviderUIID;

    @JsonProperty
    private String serviceProviderGroup;

    @JsonProperty
    private String villageLeader;

    @JsonProperty
    private String otherClinicalInformation;

    @JsonProperty
    private String otherNotes;

    @JsonProperty
    private String serviceGivenToPatient;


    @JsonProperty
    private int labTest;

    @JsonProperty
    private int testResults;

    @JsonProperty
    private long appointmentDate;

    @JsonProperty
    private boolean isEmergency;

    @JsonProperty
    private String fromFacilityId;

    @JsonProperty
    private int referralSource;

    @JsonProperty
    private int referralType;

    @JsonProperty
    private Long referralDate;

    @JsonProperty
    private String facilityId;


    @JsonProperty
    private String intanceId;

    /*
	 *  0 = new
	 * -1 = rejected/discarded
	 *  1 = complete referral
	 */
    @JsonProperty
    private int referralStatus;

    @JsonProperty
    private long updatedAt;

	@JsonProperty
    private List<Long> serviceIndicatorIds;


    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getCommunityBasedHivService() {
        return communityBasedHivService;
    }

    public void setCommunityBasedHivService(String communityBasedHivService) {
        this.communityBasedHivService = communityBasedHivService;
    }

    public String getReferralReason() {
        return referralReason;
    }

    public void setReferralReason(String referralReason) {
        this.referralReason = referralReason;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getCtcNumber() {
        return ctcNumber;
    }

    public void setCtcNumber(String ctcNumber) {
        this.ctcNumber = ctcNumber;
    }

    public String getReferralUUID() {
        return referralUUID;
    }

    public void setReferralUUID(String referralUUID) {
        this.referralUUID = referralUUID;
    }

    public String getServiceProviderUIID() {
        return serviceProviderUIID;
    }

    public void setServiceProviderUIID(String serviceProviderUIID) {
        this.serviceProviderUIID = serviceProviderUIID;
    }

    public String getServiceProviderGroup() {
        return serviceProviderGroup;
    }

    public void setServiceProviderGroup(String serviceProviderGroup) {
        this.serviceProviderGroup = serviceProviderGroup;
    }

    public Long getReferralId() {
        return referralId;
    }

    public void setReferralId(Long referralId) {
        this.referralId = referralId;
    }

    public int getTestResults() {
        return testResults;
    }

    public void setTestResults(int testResults) {
        this.testResults = testResults;
    }

    public void setReferralDate(Long referralDate) {
        this.referralDate = referralDate;
    }

    public String getVillageLeader() {
        return villageLeader;
    }

    public void setVillageLeader(String villageLeader) {
        this.villageLeader = villageLeader;
    }

    public long getReferralDate() {
        return referralDate;
    }

    public void setReferralDate(long referralDate) {
        this.referralDate = referralDate;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public int getReferralStatus() {
        return referralStatus;
    }

    public void setReferralStatus(int referralStatus) {
        this.referralStatus = referralStatus;
    }

    public String getOtherClinicalInformation() {
        return otherClinicalInformation;
    }

    public void setOtherClinicalInformation(String otherClinicalInformation) {
        this.otherClinicalInformation = otherClinicalInformation;
    }

    public String getOtherNotes() {
        return otherNotes;
    }

    public void setOtherNotes(String otherNotes) {
        this.otherNotes = otherNotes;
    }

    public String getServiceGivenToPatient() {
        return serviceGivenToPatient;
    }

    public void setServiceGivenToPatient(String serviceGivenToPatient) {
        this.serviceGivenToPatient = serviceGivenToPatient;
    }

    public int getLabTest() {
        return labTest;
    }

    public void setLabTest(int labTest) {
        this.labTest = labTest;
    }

    public int getReferralType() {
        return referralType;
    }

    public void setReferralType(int referralType) {
        this.referralType = referralType;
    }

    public String getFromFacilityId() {
        return fromFacilityId;
    }

    public void setFromFacilityId(String fromFacilityId) {
        this.fromFacilityId = fromFacilityId;
    }

    public int getReferralSource() {
        return referralSource;
    }

    public void setReferralSource(int referralSource) {
        this.referralSource = referralSource;
    }

	public List<Long> getServiceIndicatorIds() {
		return serviceIndicatorIds;
	}

	public void setServiceIndicatorIds(List<Long> serviceIndicatorIds) {
		this.serviceIndicatorIds = serviceIndicatorIds;
	}

	public long getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(long appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public boolean isEmergency() {
        return isEmergency;
    }

    public void setEmergency(boolean emergency) {
        isEmergency = emergency;
    }

    public String getIntanceId() {
        return intanceId;
    }

    public void setIntanceId(String intanceId) {
        this.intanceId = intanceId;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public final boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public final int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}