package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class TBPatientMobileClientDTO {
    @JsonProperty
    private Long id;

	@JsonProperty
	private Long patientId;

	@JsonProperty
	private String firstName;

	@JsonProperty
	private String middleName;

	@JsonProperty
	private String phoneNumber;

	@JsonProperty
	private String ward;

	@JsonProperty
	private String village;

	@JsonProperty
	private String hamlet;

	@JsonProperty
	private long dateOfBirth;

	@JsonProperty
	private String surname;

	@JsonProperty
	private String gender;

	@JsonProperty
	private boolean hivStatus;

	@JsonProperty
	private boolean isPregnant;

	@JsonProperty
	private long dateOfDeath;

    @JsonProperty
    private Long healthFacilityPatientId;

    @JsonProperty
    private int patientType;

    @JsonProperty
    private int transferType;

    @JsonProperty
    private int referralType;

    @JsonProperty
    private String veo;

    @JsonProperty
    private double weight;

    @JsonProperty
    private String xray;

    @JsonProperty
    private String makohozi;

    @JsonProperty
    private int testType;

	@JsonProperty
	private String otherTestsDetails;

    @JsonProperty
    private String treatment_type;

    @JsonProperty
    private String outcome;

    @JsonProperty
    private long outcomeDate;

    @JsonProperty
    private String outcomeDetails;

	@JsonProperty
	private String healthFacilityCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getHamlet() {
		return hamlet;
	}

	public void setHamlet(String hamlet) {
		this.hamlet = hamlet;
	}

	public long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(long dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public Long getHealthFacilityPatientId() {
		return healthFacilityPatientId;
	}

	public void setHealthFacilityPatientId(Long healthFacilityPatientId) {
		this.healthFacilityPatientId = healthFacilityPatientId;
	}

	public int getPatientType() {
		return patientType;
	}

	public void setPatientType(int patientType) {
		this.patientType = patientType;
	}

	public int getTransferType() {
		return transferType;
	}

	public void setTransferType(int transferType) {
		this.transferType = transferType;
	}

	public int getReferralType() {
		return referralType;
	}

	public void setReferralType(int referralType) {
		this.referralType = referralType;
	}

	public String getVeo() {
		return veo;
	}

	public void setVeo(String veo) {
		this.veo = veo;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getXray() {
		return xray;
	}

	public void setXray(String xray) {
		this.xray = xray;
	}

	public String getMakohozi() {
		return makohozi;
	}

	public void setMakohozi(String makohozi) {
		this.makohozi = makohozi;
	}

	public int getTestType() {
		return testType;
	}

	public void setTestType(int testType) {
		this.testType = testType;
	}

	public String getOtherTestsDetails() {
		return otherTestsDetails;
	}

	public void setOtherTestsDetails(String otherTestsDetails) {
		this.otherTestsDetails = otherTestsDetails;
	}

	public String getTreatment_type() {
		return treatment_type;
	}

	public void setTreatment_type(String treatment_type) {
		this.treatment_type = treatment_type;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public long getOutcomeDate() {
		return outcomeDate;
	}

	public void setOutcomeDate(long outcomeDate) {
		this.outcomeDate = outcomeDate;
	}

	public String getOutcomeDetails() {
		return outcomeDetails;
	}

	public void setOutcomeDetails(String outcomeDetails) {
		this.outcomeDetails = outcomeDetails;
	}

	public String getHealthFacilityCode() {
		return healthFacilityCode;
	}

	public void setHealthFacilityCode(String healthFacilityCode) {
		this.healthFacilityCode = healthFacilityCode;
	}

	public boolean isHivStatus() {
		return hivStatus;
	}

	public void setHivStatus(boolean hivStatus) {
		this.hivStatus = hivStatus;
	}

	public boolean isPregnant() {
		return isPregnant;
	}

	public void setPregnant(boolean pregnant) {
		isPregnant = pregnant;
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
