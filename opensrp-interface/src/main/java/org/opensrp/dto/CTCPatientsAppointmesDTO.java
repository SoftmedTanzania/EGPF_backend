package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;
import java.util.Map;

public class CTCPatientsAppointmesDTO {

    @JsonProperty
    private Long rowVersion;

    @JsonProperty
    private boolean cancelled;

    @JsonProperty
    private Long dateOfAppointment;

    public Long getRowVersion() {
        return rowVersion;
    }

    public void setRowVersion(Long rowVersion) {
        this.rowVersion = rowVersion;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Long getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(Long dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
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
