package org.opensrp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_services")
public class ReferralService {

	public static final String tbName = "tbl_services";

	public static final String COL_REFERRAL_SERVICE_NAME = "referral_service_name";

	public static final String COL_REFERRAL_SERVICE_NAME_SW = "referral_service_name_sw";

	public static final String COL_REFERRAL_SERVICE_ID= "referral_service_id";

	public static final String COL_REFERRAL_CATEGORY_NAME= "referral_category_name";

	public static final String COL_IS_ACTIVE = "is_active";

	public static final String COL_CREATED_AT = "created_at";

	public static final String COL_UPDATED_AT = "updated_at";

	@Id
	@GeneratedValue
	@Column(name = COL_REFERRAL_SERVICE_ID)
	private Long referralServiceId;

	@Column(name = COL_REFERRAL_SERVICE_NAME,unique = true)
	private String referralServiceName;

	@Column(name = COL_REFERRAL_SERVICE_NAME_SW,unique = true)
	private String referralServiceNameSw;

	@Column(name = COL_REFERRAL_CATEGORY_NAME)
	private String referralCategoryName;


	@Column(name = COL_IS_ACTIVE)
	private boolean isActive;

	@Column(name = COL_CREATED_AT, columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = COL_UPDATED_AT, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public Long getReferralServiceId() {
		return referralServiceId;
	}

	public void setReferralServiceId(Long referralServiceId) {
		this.referralServiceId = referralServiceId;
	}

	public String getReferralServiceName() {
		return referralServiceName;
	}

	public void setReferralServiceName(String referralServiceName) {
		this.referralServiceName = referralServiceName;
	}

	public String getReferralCategoryName() {
		return referralCategoryName;
	}

	public void setReferralCategoryName(String referralCategoryName) {
		this.referralCategoryName = referralCategoryName;
	}

	public String getReferralServiceNameSw() {
		return referralServiceNameSw;
	}

	public void setReferralServiceNameSw(String referralServiceNameSw) {
		this.referralServiceNameSw = referralServiceNameSw;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
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
