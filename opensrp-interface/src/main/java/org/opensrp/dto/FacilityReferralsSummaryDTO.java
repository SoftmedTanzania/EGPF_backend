package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class FacilityReferralsSummaryDTO {
    @JsonProperty
    private String facilityName;
    @JsonProperty
    private List<FacilityDepartmentReferralSummaryDTO> departmentReferralSummaryDTOList;

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public List<FacilityDepartmentReferralSummaryDTO> getDepartmentReferralSummaryDTOList() {
        return departmentReferralSummaryDTOList;
    }

    public void setDepartmentReferralSummaryDTOList(List<FacilityDepartmentReferralSummaryDTO> departmentReferralSummaryDTOList) {
        this.departmentReferralSummaryDTOList = departmentReferralSummaryDTOList;
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

