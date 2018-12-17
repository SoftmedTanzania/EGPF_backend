package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class ReferralServiceDTO {
    @JsonProperty
    private Long id;

    @JsonProperty
    private String serviceName;

    @JsonProperty
    private String referralServiceNameSw;

    @JsonProperty
    private String category;

    @JsonProperty
    private boolean isActive;


    public ReferralServiceDTO(Long id, String serviceName, String category, boolean isActive) {
        this.id = id;
        this.serviceName = serviceName;
        this.category = category;
        this.isActive = isActive;
    }

    public ReferralServiceDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getReferralServiceNameSw() {
        return referralServiceNameSw;
    }

    public void setReferralServiceNameSw(String referralServiceNameSw) {
        this.referralServiceNameSw = referralServiceNameSw;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
