package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class TBCompletePatientDataDTO {
    @JsonProperty
    private PatientsDTO patientsDTO;

    @JsonProperty
    private TBPatientDTO tbPatientDTO;

    @JsonProperty
    private List<PatientsAppointmentsDTO> patientsAppointmentsDTOS;


    @JsonProperty
    private List<TBEncounterDTO> tbEncounterDTOS;

    public PatientsDTO getPatientsDTO() {
        return patientsDTO;
    }

    public void setPatientsDTO(PatientsDTO patientsDTO) {
        this.patientsDTO = patientsDTO;
    }

    public List<PatientsAppointmentsDTO> getPatientsAppointmentsDTOS() {
        return patientsAppointmentsDTOS;
    }

    public void setPatientsAppointmentsDTOS(List<PatientsAppointmentsDTO> patientsAppointmentsDTOS) {
        this.patientsAppointmentsDTOS = patientsAppointmentsDTOS;
    }

    public TBPatientDTO getTbPatientDTO() {
        return tbPatientDTO;
    }

    public void setTbPatientDTO(TBPatientDTO tbPatientDTO) {
        this.tbPatientDTO = tbPatientDTO;
    }

    public List<TBEncounterDTO> getTbEncounterDTOS() {
        return tbEncounterDTOS;
    }

    public void setTbEncounterDTOS(List<TBEncounterDTO> tbEncounterDTOS) {
        this.tbEncounterDTOS = tbEncounterDTOS;
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
