package com.ride.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseModel implements Serializable {

	private static final long serialVersionUID = 1L;

    @JsonProperty("apiVersion")
	private Double apiVersion;
    
    @JsonProperty("requestedBy")
	private String requestedBy; ///CAN BE LONG
    
    @JsonProperty("requestedTime")
	private LocalDateTime requestedTime;
	
    @JsonProperty("totalRecords")
	private Long totalRecords;
	
    @JsonProperty("requestedDataSet")
	private String requestedDataSet;
	
    @JsonProperty("status")
	private Status status;
    
    @JsonProperty("responseData")
	private Object data;
	
	public enum Status {
	    SUCCESS,FAILURE;
	}
}
