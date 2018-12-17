package org.opensrp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_patients")
public class Patients {

	public static final String tbName = "tbl_patients";

	public static final String COL_PATIENT_ID = "patient_id";

	public static final String COL_PATIENT_FIRST_NAME = "first_name";

	public static final String COL_PATIENT_MIDDLE_NAME = "middle_name";

	public static final String COL_PATIENT_SURNAME = "surname";

	public static final String COL_PHONE_NUMBER = "phone_number";

	public static final String COL_WARD = "ward";

	public static final String COL_VILLAGE = "village";

	public static final String COL_HAMLET = "hamlet";

	public static final String COL_DATE_OF_BIRTH = "date_of_birth";

	public static final String COL_GENDER = "gender";

	public static final String COL_DATE_OF_DEATH= "date_of_death";

	public static final String COL_HIV_STATUS= "hiv_status";

	public static final String COL_COMMUNITY_BASED_HIV_SERVICE= "community_based_hiv_service";

	public static final String COL_CARE_TAKER_NAME= "care_taker_name";

	public static final String COL_CARE_TAKER_PHONE_NUMBER= "care_taker_phone_number";

	public static final String COL_CARE_TAKER_RELATIONSHIP= "care_taker_relationship";

	public static final String COL_CREATED_AT = "created_at";

	public static final String COL_UPDATED_AT = "updated_at";

	@Id
	@GeneratedValue
	@Column(name = COL_PATIENT_ID)
	private Long patientId;

	@Column(name = COL_PATIENT_FIRST_NAME)
	private String firstName;


	@Column(name = COL_PATIENT_MIDDLE_NAME)
	private String middleName;

	@Column(name = COL_PATIENT_SURNAME)
	private String surname;

	@Column(name = COL_PHONE_NUMBER)
	private String phoneNumber;

	@Column(name = COL_WARD)
	private String ward;

	@Column(name = COL_VILLAGE)
	private String village;

	@Column(name = COL_HAMLET)
	private String hamlet;

	@Column(name = COL_DATE_OF_BIRTH)
	private Date dateOfBirth;

	@Column(name = COL_GENDER)
	private String gender;

	@Column(name = COL_DATE_OF_DEATH)
	private Date dateOfDeath;

	@Column(name = COL_HIV_STATUS)
	private boolean hivStatus;

	@Column(name = COL_COMMUNITY_BASED_HIV_SERVICE)
	private String communityBasedHivService;


	@Column(name = COL_CARE_TAKER_NAME)
	private String careTakerName;


	@Column(name = COL_CARE_TAKER_PHONE_NUMBER)
	private String careTakerPhoneNumber;


	@Column(name = COL_CARE_TAKER_RELATIONSHIP)
	private String careTakerRelationship;



	@Column(name = COL_CREATED_AT, columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = COL_UPDATED_AT, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
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

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}

