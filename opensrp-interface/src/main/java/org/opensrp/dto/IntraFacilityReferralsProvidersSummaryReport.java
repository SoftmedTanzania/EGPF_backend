package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class IntraFacilityReferralsProvidersSummaryReport {
    @JsonProperty
    private String facilityName;
    @JsonProperty
    private List<FacilityProvidersReferralSummaryDTO> summaryDTOS;

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public List<FacilityProvidersReferralSummaryDTO> getSummaryDTOS() {
        return summaryDTOS;
    }

    public void setSummaryDTOS(List<FacilityProvidersReferralSummaryDTO> summaryDTOS) {
        this.summaryDTOS = summaryDTOS;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

