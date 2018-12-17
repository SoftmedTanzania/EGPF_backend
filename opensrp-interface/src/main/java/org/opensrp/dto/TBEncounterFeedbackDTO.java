package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class TBEncounterFeedbackDTO {
	@JsonProperty
	private TBEncounterDTO tbEncounterDTO;

	@JsonProperty
	private List<PatientsAppointmentsDTO> patientsAppointmentsDTOS;

	public TBEncounterDTO getTbEncounterDTO() {
		return tbEncounterDTO;
	}

	public void setTbEncounterDTO(TBEncounterDTO tbEncounterDTO) {
		this.tbEncounterDTO = tbEncounterDTO;
	}

	public List<PatientsAppointmentsDTO> getPatientsAppointmentsDTOS() {
		return patientsAppointmentsDTOS;
	}

	public void setPatientsAppointmentsDTOS(List<PatientsAppointmentsDTO> patientsAppointmentsDTOS) {
		this.patientsAppointmentsDTOS = patientsAppointmentsDTOS;
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
