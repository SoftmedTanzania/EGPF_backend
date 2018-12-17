package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class HealthFacilitiesDTO {

    @JsonProperty
    private String facilityId;

    @JsonProperty
    private String facilityName;

    @JsonProperty
    private String facilityCTCCode;

    @JsonProperty
    private String openmrsUIID;

	@JsonProperty
	private String parentOpenmrsUIID;

	@JsonProperty
	private String hfrCode;

    @JsonProperty
    private String parentHFRCode;


	public String getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getFacilityCTCCode() {
		return facilityCTCCode;
	}

	public void setFacilityCTCCode(String facilityCTCCode) {
		this.facilityCTCCode = facilityCTCCode;
	}

	public String getOpenmrsUIID() {
		return openmrsUIID;
	}

	public void setOpenmrsUIID(String openmrsUIID) {
		this.openmrsUIID = openmrsUIID;
	}

	public String getParentOpenmrsUIID() {
		return parentOpenmrsUIID;
	}

	public void setParentOpenmrsUIID(String parentOpenmrsUIID) {
		this.parentOpenmrsUIID = parentOpenmrsUIID;
	}

	public String getHfrCode() {
		return hfrCode;
	}

	public void setHfrCode(String hfrCode) {
		this.hfrCode = hfrCode;
	}

	public String getParentHFRCode() {
		return parentHFRCode;
	}

	public void setParentHFRCode(String parentHFRCode) {
		this.parentHFRCode = parentHFRCode;
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
