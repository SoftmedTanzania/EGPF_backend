package org.opensrp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_indicator_signs")
public class Indicator {

	public static final String tbName = "tbl_indicator_signs";

	public static final String COL_REFERRAL_INDICATOR_NAME = "referral_indicator_name";

	public static final String COL_REFERRAL_INDICATOR_NAME_SW = "referral_indicator_name_sw";

	public static final String COL_REFERRAL_INDICATOR_ID= "referral_indicator_id";

	public static final String COL_IS_ACTIVE = "is_active";

	public static final String COL_CREATED_AT = "created_at";

	public static final String COL_UPDATED_AT = "updated_at";

	@Id
	@GeneratedValue
	@Column(name = COL_REFERRAL_INDICATOR_ID)
	private Long referralIndicatorId;

	@Column(name = COL_REFERRAL_INDICATOR_NAME,unique = true)
	private String referralIndicatorName;

	@Column(name = COL_REFERRAL_INDICATOR_NAME_SW,unique = true)
	private String referralIndicatorNameSw;

	@Column(name = COL_IS_ACTIVE)
	private boolean isActive;

	@Column(name = COL_CREATED_AT, columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = COL_UPDATED_AT, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public Long getReferralIndicatorId() {
		return referralIndicatorId;
	}

	public void setReferralIndicatorId(Long referralIndicatorId) {
		this.referralIndicatorId = referralIndicatorId;
	}

	public String getReferralIndicatorName() {
		return referralIndicatorName;
	}

	public void setReferralIndicatorName(String referralIndicatorName) {
		this.referralIndicatorName = referralIndicatorName;
	}

	public String getReferralIndicatorNameSw() {
		return referralIndicatorNameSw;
	}

	public void setReferralIndicatorNameSw(String referralIndicatorNameSw) {
		this.referralIndicatorNameSw = referralIndicatorNameSw;
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
