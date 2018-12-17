package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class PatientsDTO {
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
    private Long dateOfDeath;

    @JsonProperty
    private boolean hivStatus;

    @JsonProperty
    private String communityBasedHivService;


    @JsonProperty
    private String careTakerName;


    @JsonProperty
    private String careTakerPhoneNumber;


    @JsonProperty
    private String careTakerRelationship;


    @JsonProperty
    private String healthFacilityCode;


    @JsonProperty
    private String ctcNumber;

    @JsonProperty
    private boolean currentOnTbTreatment;


    @JsonProperty
    private Long updatedAt;


    public PatientsDTO() {
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

    public Long getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Long dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public boolean isHivStatus() {
        return hivStatus;
    }

    public void setHivStatus(boolean hivStatus) {
        this.hivStatus = hivStatus;
    }

    public String getCommunityBasedHivService() {
        return communityBasedHivService;
    }

    public void setCommunityBasedHivService(String communityBasedHivService) {
        this.communityBasedHivService = communityBasedHivService;
    }

    public String getCtcNumber() {
        return ctcNumber;
    }

    public void setCtcNumber(String ctcNumber) {
        this.ctcNumber = ctcNumber;
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

    public String getHealthFacilityCode() {
        return healthFacilityCode;
    }

    public void setHealthFacilityCode(String healthFacilityCode) {
        this.healthFacilityCode = healthFacilityCode;
    }

    public boolean isCurrentOnTbTreatment() {
        return currentOnTbTreatment;
    }

    public void setCurrentOnTbTreatment(boolean currentOnTbTreatment) {
        this.currentOnTbTreatment = currentOnTbTreatment;
    }


    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
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
