package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class IndicatorDTO {
    @JsonProperty
    private Long referralServiceIndicatorId;

    @JsonProperty
    private Long referralIndicatorId;

    @JsonProperty
    private String indicatorName;

    @JsonProperty
    private String indicatorNameSw;

    @JsonProperty
    private boolean isActive;

    public IndicatorDTO(Long referralServiceIndicatorId, Long referralIndicatorId, String indicatorName,String indicatorNameSw, boolean isActive) {
        this.referralServiceIndicatorId = referralServiceIndicatorId;
        this.referralIndicatorId = referralIndicatorId;
        this.indicatorName = indicatorName;
        this.indicatorNameSw = indicatorNameSw;
        this.isActive = isActive;
    }

    public Long getReferralServiceIndicatorId() {
        return referralServiceIndicatorId;
    }

    public void setReferralServiceIndicatorId(Long referralServiceIndicatorId) {
        this.referralServiceIndicatorId = referralServiceIndicatorId;
    }

    public Long getReferralIndicatorId() {
        return referralIndicatorId;
    }

    public void setReferralIndicatorId(Long referralIndicatorId) {
        this.referralIndicatorId = referralIndicatorId;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public IndicatorDTO() {
    }

    public String getIndicatorNameSw() {
        return indicatorNameSw;
    }

    public void setIndicatorNameSw(String indicatorNameSw) {
        this.indicatorNameSw = indicatorNameSw;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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
