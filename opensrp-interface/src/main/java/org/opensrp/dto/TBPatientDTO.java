package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class TBPatientDTO {
	@JsonProperty
	private Long tbPatientId;

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
	private boolean isPregnant;

	public Long getTbPatientId() {
		return tbPatientId;
	}

	public void setTbPatientId(Long tbPatientId) {
		this.tbPatientId = tbPatientId;
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
