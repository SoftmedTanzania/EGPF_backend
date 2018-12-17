package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class CTCPatientsDTO {
	@JsonProperty
	private String firstName;

	@JsonProperty
	private String middleName;

	@JsonProperty
	private String ctcNumber;

	@JsonProperty
	private String contact;

	@JsonProperty
	private Long dateOfBirth;

	@JsonProperty
	private String surname;

	@JsonProperty
	private String gender;

	@JsonProperty
	private Long dateOfDeath;


	@JsonProperty
	private String communityBasedHivService;


	@JsonProperty
	private String careTakerName;


	@JsonProperty
	private String careTakerPhoneNumber;


	@JsonProperty
	private String careTakerRelationship;


	@JsonProperty
	private boolean hivStatus;

	@JsonProperty
	private String healthFacilityCode;

	@JsonProperty
	private List<CTCPatientsAppointmesDTO> patientAppointments;

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

	public String getCtcNumber() {
		return ctcNumber;
	}

	public void setCtcNumber(String ctcNumber) {
		this.ctcNumber = ctcNumber;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Long dateOfBirth) {
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

	public Long getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(Long dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public List<CTCPatientsAppointmesDTO> getPatientAppointments() {
		return patientAppointments;
	}

	public void setPatientAppointments(List<CTCPatientsAppointmesDTO> patientAppointments) {
		this.patientAppointments = patientAppointments;
	}

	public boolean isHivStatus() {
		return hivStatus;
	}

	public void setHivStatus(boolean hivStatus) {
		this.hivStatus = hivStatus;
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

	public String getHealthFacilityCode() {
		return healthFacilityCode;
	}

	public void setHealthFacilityCode(String healthFacilityCode) {
		this.healthFacilityCode = healthFacilityCode;
	}

	public String getCommunityBasedHivService() {
		return communityBasedHivService;
	}

	public void setCommunityBasedHivService(String communityBasedHivService) {
		this.communityBasedHivService = communityBasedHivService;
	}

	public String getCareTakerName() {
		return careTakerName;
	}

	public void setCareTakerName(String careTakerName) {
		this.careTakerName = careTakerName;
	}

	public String getCareTakerPhoneNumber() {
		return careTakerPhoneNumber;
	}

	public void setCareTakerPhoneNumber(String careTakerPhoneNumber) {
		this.careTakerPhoneNumber = careTakerPhoneNumber;
	}

	public String getCareTakerRelationship() {
		return careTakerRelationship;
	}

	public void setCareTakerRelationship(String careTakerRelationship) {
		this.careTakerRelationship = careTakerRelationship;
	}
}
