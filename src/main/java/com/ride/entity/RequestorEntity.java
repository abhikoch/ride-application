package com.ride.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ride.enumeration.AssetSensitivity;
import com.ride.enumeration.AssetType;
import com.ride.enumeration.Status;

import lombok.Data;

/**
 * @author Kochhar, Abhinav 
 *
 */
@Data
@Entity
@Table(name = "REQUESTOR")
public class RequestorEntity {
	
	@Id
    @Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "FROM_LOCATION")
    private String fromLocation;
    
    @Column(name = "TO_LOCATION")
    private String toLocation;
    
    @Column(name = "DATE_AND_TIME")
    private LocalDateTime dateAndTime;
    
    @Column(name = "FELEXIBLE_TIMINGS")
    private Boolean fexibleTimings;
    
    @Column(name = "NUMBER_OF_ASSETS")
    private Integer noOfAssets;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ASSET_TYPE")
    private AssetType assetType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ASSET_SENSITIVITY")
    private AssetSensitivity assetSensitivity;
    
    @Column(name = "WHOM_TO_DELIVER")
    private String whomToDeliver;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;
	
}