package com.app.service.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Request {

	@JsonProperty(value = "userCode", access = Access.WRITE_ONLY, required = true)
	@NotBlank(message = "User Code is required")
	private String in_NUSERID;

	@JsonProperty(value = "inDateTime", access = Access.WRITE_ONLY, required = true)
	@NotBlank(message = "ATS date is required in format (DD-MM-YYYY HH24:MI SS)")
	private String in_NDATETIME;

	@JsonProperty(value = "inWorkLongitude", access = Access.WRITE_ONLY, required = true)
	@NotNull(message = "Work Longitude position is required")
	private Double in_work_long_pos;

	@JsonProperty(value = "inWorkLatitude", access = Access.WRITE_ONLY, required = true)
	@NotNull(message = "Work Latitude position is required")
	private Double in_work_lat_pos;

	@JsonProperty(value = "inAttLongitude", access = Access.WRITE_ONLY, required = true)
	@NotNull(message = "Attendance Longitude position is required")
	private Double in_ATT_long_pos;

	@JsonProperty(value = "inAttLatitude", access = Access.WRITE_ONLY, required = true)
	@NotNull(message = "Attendance Latitude position is required")
	private Double in_ATT_lat_pos;

	public String getIn_NUSERID() {
		return in_NUSERID;
	}

	public void setIn_NUSERID(String in_NUSERID) {
		this.in_NUSERID = in_NUSERID;
	}

	public String getIn_NDATETIME() {
		return in_NDATETIME;
	}

	public void setIn_NDATETIME(String in_NDATETIME) {
		this.in_NDATETIME = in_NDATETIME;
	}

	public Double getIn_work_long_pos() {
		return in_work_long_pos;
	}

	public void setIn_work_long_pos(Double in_work_long_pos) {
		this.in_work_long_pos = in_work_long_pos;
	}

	public Double getIn_work_lat_pos() {
		return in_work_lat_pos;
	}

	public void setIn_work_lat_pos(Double in_work_lat_pos) {
		this.in_work_lat_pos = in_work_lat_pos;
	}

	public Double getIn_ATT_long_pos() {
		return in_ATT_long_pos;
	}

	public void setIn_ATT_long_pos(Double in_ATT_long_pos) {
		this.in_ATT_long_pos = in_ATT_long_pos;
	}

	public Double getIn_ATT_lat_pos() {
		return in_ATT_lat_pos;
	}

	public void setIn_ATT_lat_pos(Double in_ATT_lat_pos) {
		this.in_ATT_lat_pos = in_ATT_lat_pos;
	}

	@Override
	public String toString() {
		return "{" +
				"in_NUSERID='" + in_NUSERID + '\'' +
				", in_NDATETIME='" + in_NDATETIME + '\'' +
				", in_work_long_pos=" + in_work_long_pos +
				", in_work_lat_pos=" + in_work_lat_pos +
				", in_ATT_long_pos=" + in_ATT_long_pos +
				", in_ATT_lat_pos=" + in_ATT_lat_pos +
				'}';
	}
}
