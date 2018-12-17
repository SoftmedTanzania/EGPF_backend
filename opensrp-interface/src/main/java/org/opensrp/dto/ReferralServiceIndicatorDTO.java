package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class ReferralServiceIndicatorDTO {
    @JsonProperty
    private Long referralServiceId;

    @JsonProperty
    private List<Long> referralIndicatorId;


    public Long getReferralServiceId() {
        return referralServiceId;
    }

    public void setReferralServiceId(Long referralServiceId) {
        this.referralServiceId = referralServiceId;
    }

    public List<Long> getReferralIndicatorId() {
        return referralIndicatorId;
    }

    public void setReferralIndicatorId(List<Long> referralIndicatorId) {
        this.referralIndicatorId = referralIndicatorId;
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
