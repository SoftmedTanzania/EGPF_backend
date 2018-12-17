package org.opensrp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_referral_type")
public class ReferralType {

	public static final String tbName = "tbl_referral_type";

	public static final String COL_REFERRAL_TYPE_NAME = "referral_type_name";

	public static final String COL_REFERRAL_TYPE_ID= "referral_type_id";

	public static final String COL_IS_ACTIVE = "is_active";

	public static final String COL_CREATED_AT = "created_at";

	public static final String COL_UPDATED_AT = "updated_at";

	@Id
	@GeneratedValue
	@Column(name = COL_REFERRAL_TYPE_ID)
	private Long referralTypeId;

	@Column(name = COL_REFERRAL_TYPE_NAME,unique = true)
	private String referralTypeName;

	@Column(name = COL_IS_ACTIVE)
	private boolean isActive;

	@Column(name = COL_CREATED_AT, columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = COL_UPDATED_AT, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public Long getReferralTypeId() {
		return referralTypeId;
	}

	public void setReferralTypeId(Long referralTypeId) {
		this.referralTypeId = referralTypeId;
	}

	public String getReferralTypeName() {
		return referralTypeName;
	}

	public void setReferralTypeName(String referralTypeName) {
		this.referralTypeName = referralTypeName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
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
