package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class TBEncounterDTO {
    @JsonProperty
	private Long id;

	@JsonProperty
	private Long tbPatientId;

	@JsonProperty
	private double weight;

	@JsonProperty
	private String makohozi;

	@JsonProperty
	private Long appointmentId;

	@JsonProperty
	private int encounterMonth;

	@JsonProperty
	private int encounterYear;

	@JsonProperty
	private String localID;

	@JsonProperty
	private boolean hasFinishedPreviousMonthMedication;

	@JsonProperty
	private long scheduledDate;


	@JsonProperty
	private long medicationDate;

	@JsonProperty
	private boolean medicationStatus;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTbPatientId() {
		return tbPatientId;
	}

	public void setTbPatientId(Long tbPatientId) {
		this.tbPatientId = tbPatientId;
	}

	public String getLocalID() {
		return localID;
	}

	public void setLocalID(String localID) {
		this.localID = localID;
	}

	public String getMakohozi() {
		return makohozi;
	}

	public void setMakohozi(String makohozi) {
		this.makohozi = makohozi;
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getEncounterMonth() {
		return encounterMonth;
	}

	public void setEncounterMonth(int encounterMonth) {
		this.encounterMonth = encounterMonth;
	}

	public boolean isHasFinishedPreviousMonthMedication() {
		return hasFinishedPreviousMonthMedication;
	}

	public void setHasFinishedPreviousMonthMedication(boolean hasFinishedPreviousMonthMedication) {
		this.hasFinishedPreviousMonthMedication = hasFinishedPreviousMonthMedication;
	}

	public int getEncounterYear() {
		return encounterYear;
	}

	public void setEncounterYear(int encounterYear) {
		this.encounterYear = encounterYear;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public long getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(long scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public long getMedicationDate() {
		return medicationDate;
	}

	public void setMedicationDate(long medicationDate) {
		this.medicationDate = medicationDate;
	}

	public boolean isMedicationStatus() {
		return medicationStatus;
	}

	public void setMedicationStatus(boolean medicationStatus) {
		this.medicationStatus = medicationStatus;
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
