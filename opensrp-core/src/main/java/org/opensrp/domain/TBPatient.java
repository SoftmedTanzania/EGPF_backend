package org.opensrp.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_tb_patients")
public class TBPatient  {

	public static final String tbName = "tbl_tb_patients";

	public static final String COL_HEALTH_FACILITY_PATIENT_ID = "health_facility_patient_id";

	public static final String COL_PATIENT_TYPE = "patient_type";

	public static final String COL_TB_PATIENT_ID = "tb_patient_id";

	public static final String COL_TRANSFER_TYPE= "transfer_type";

	public static final String COL_REFERRAL_TYPE = "referral_type";

	public static final String COL_VEO= "veo";

	public static final String COL_TEST_TYPE = "test_type";

	public static final String COL_WEIGHT = "weight";

	public static final String COL_XRAY = "xray";

	public static final String COL_MAKOHOZI = "makohozi";

	public static final String COL_OTHER_TESTS_DETAILS = "other_tests_details";

	public static final String COL_TREATMENT_TYPE = "treatment_type";

	public static final String COL_OUTCOME = "outcome";

	public static final String COL_OUTCOME_DATE = "outcome_date";

	public static final String COL_IS_PREGNANT = "is_pregnant";

	public static final String COL_OUTCOME_DETAILS= "outcome_details";

	public static final String COL_CREATED_AT = "created_at";

	public static final String COL_UPDATED_AT = "updated_at";

	@Id
	@GeneratedValue
	@Column(name = COL_TB_PATIENT_ID)
	private Long tbPatientId;

	//TODO recheck this implementation for reoccuring tb cases
	@OneToOne
	@JoinColumn(name=COL_HEALTH_FACILITY_PATIENT_ID,unique=true)
	private HealthFacilitiesPatients healthFacilitiesPatients;

	@Column(name = COL_PATIENT_TYPE)
	private int patientType;

	@Column(name = COL_TRANSFER_TYPE)
	private int transferType;

	@Column(name = COL_REFERRAL_TYPE)
	private int referralType;

	@Column(name = COL_VEO)
	private String veo;

	@Column(name = COL_TEST_TYPE)
	private int testType;

	@Column(name = COL_OTHER_TESTS_DETAILS)
	private String otherTestsDetails;

	@Column(name = COL_WEIGHT)
	private double weight;

	@Column(name = COL_XRAY)
	private String xray;

	@Column(name = COL_MAKOHOZI)
	private String makohozi;

	@Column(name = COL_TREATMENT_TYPE)
	private String treatment_type;

	@Column(name = COL_OUTCOME)
	private String outcome;

	@Column(name = COL_OUTCOME_DATE)
	private Date outcomeDate;

	@Column(name = COL_OUTCOME_DETAILS)
	private String outcomeDetails;

	@Column(name = COL_IS_PREGNANT)
	private boolean isPregnant;


	@Column(name = COL_CREATED_AT, columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = COL_UPDATED_AT, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public Long getTbPatientId() {
		return tbPatientId;
	}

	public void setTbPatientId(Long tbPatientId) {
		this.tbPatientId = tbPatientId;
	}

	public HealthFacilitiesPatients getHealthFacilitiesPatients() {
		return healthFacilitiesPatients;
	}

	public void setHealthFacilitiesPatients(HealthFacilitiesPatients healthFacilitiesPatients) {
		this.healthFacilitiesPatients = healthFacilitiesPatients;
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

	public Date getOutcomeDate() {
		return outcomeDate;
	}

	public void setOutcomeDate(Date outcomeDate) {
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
