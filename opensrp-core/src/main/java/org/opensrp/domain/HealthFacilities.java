package org.opensrp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_health_facilities")
public class HealthFacilities {

	public static final String tbName = "tbl_health_facilities";

	public static final String COL_OPENMRS_UIID = "openmrs_UIID";

	public static final String COL_FACILITY_NAME = "facility_name";

	public static final String COL_FACILITY_CTC_CODE = "facility_ctc_code";

	public static final String COL_PARENT_OPENMRS_UIID= "parent_openmrs_UIID";

	public static final String COL_PARENT_HFR_CODE= "parent_HFR_code";

	public static final String COL_HFR_CODE = "HFR_code";

	public static final String COL_CREATED_AT = "created_at";

	public static final String COL_UPDATED_AT = "updated_at";

	@Id
	@GeneratedValue
	@Column(name = "_id")
	private Long id;

	@Column(name = COL_OPENMRS_UIID,unique=true)
	private String openMRSUIID;

	@Column(name = COL_FACILITY_NAME)
	private String facilityName;

	@Column(name = COL_FACILITY_CTC_CODE,unique=true)
	private String facilityCtcCode;

	@Column(name = COL_PARENT_OPENMRS_UIID)
	private String parentOpenmrsUIID;

	@Column(name = COL_PARENT_HFR_CODE)
	private String parentHFRCode;


	@Column(name = COL_HFR_CODE,unique=true)
	private String hfrCode;

	@Column(name = COL_CREATED_AT, columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = COL_UPDATED_AT, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpenMRSUIID() {
		return openMRSUIID;
	}

	public void setOpenMRSUIID(String openMRSUIID) {
		this.openMRSUIID = openMRSUIID;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getFacilityCtcCode() {
		return facilityCtcCode;
	}

	public void setFacilityCtcCode(String facilityCtcCode) {
		this.facilityCtcCode = facilityCtcCode;
	}

	public String getHfrCode() {
		return hfrCode;
	}

	public void setHfrCode(String hfrCode) {
		this.hfrCode = hfrCode;
	}

	public String getParentOpenmrsUIID() {
		return parentOpenmrsUIID;
	}

	public void setParentOpenmrsUIID(String parentOpenmrsUIID) {
		this.parentOpenmrsUIID = parentOpenmrsUIID;
	}

	public String getParentHFRCode() {
		return parentHFRCode;
	}

	public void setParentHFRCode(String parentHFRCode) {
		this.parentHFRCode = parentHFRCode;
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
