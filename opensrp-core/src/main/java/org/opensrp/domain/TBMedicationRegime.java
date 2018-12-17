package org.opensrp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_tb_medication_regimes")
public class TBMedicationRegime {

	public static final String tbName = "tbl_tb_medication_regimes";

	public static final String COL_MEDICATION_REGIME_NAME = "medication_regime_name";

	public static final String COL_IS_ACTIVE = "is_active";

	public static final String COL_CREATED_AT = "created_at";

	public static final String COL_UPDATED_AT = "updated_at";


	@GeneratedValue
	@Column(name = "_id")
	private Long id;

	@Id
	@Column(name = COL_MEDICATION_REGIME_NAME)
	private String medicationRegimeName;

	@Column(name = COL_IS_ACTIVE)
	private boolean isActive;

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

	public String getMedicationRegimeName() {
		return medicationRegimeName;
	}

	public void setMedicationRegimeName(String medicationRegimeName) {
		this.medicationRegimeName = medicationRegimeName;
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
