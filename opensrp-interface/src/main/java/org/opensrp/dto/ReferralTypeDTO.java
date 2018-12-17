package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class ReferralTypeDTO {
    @JsonProperty
    private Long referralTypeId;

    @JsonProperty
    private String referralTypeName;

    @JsonProperty
    private boolean isActive;

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
