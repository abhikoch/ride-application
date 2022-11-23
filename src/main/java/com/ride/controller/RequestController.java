package com.ride.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ride.constant.RideBackendApiResponse;
import com.ride.service.RequestService;
import com.ride.constant.RideBackendPageableParam;
import com.ride.entity.RequestorEntity;
import com.ride.entity.RiderEntity;
import com.ride.model.PaginationModel;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Kochhar, Abhinav 
 *
 */
@Slf4j
@RestController
@RequestMapping(path = "/request")
public class RequestController {

	@Autowired
	RequestService requestService;

	@ApiOperation(value = "Get the list of Requestor Requests", response = RequestorEntity.class)
	@RideBackendApiResponse
	@RideBackendPageableParam
	@GetMapping(value = "/requestor", produces = "application/json")
	public PaginationModel<RequestorEntity> getAllRequestorRequests(@ApiIgnore Pageable pageable,
			@ApiParam(name = "search", type = "String", value = "Query string for searching by column values", example = "walletId:1,2,3", required = false) @RequestParam(value = "search", required = false) Optional<String> searchString) {
		log.info("Entered getAllRequestorRequests of RequestController");
		return requestService.getAllRequestorRequests(pageable, searchString);
	}

	@ApiOperation(value = "Create a new Requestor Request", response = RequestorEntity.class)
	@RideBackendApiResponse
	@PostMapping(value = "/requestor", produces = "application/json")
	public Integer saveRequestorRequest(@RequestBody RequestorEntity requestorEntity) {
		log.info("Entered saveRequestorRequest of RequestController");
		return requestService.saveRequestorRequest(requestorEntity);
	}

	@ApiOperation(value = "Create a new Requestor Request", response = RequestorEntity.class)
	@RideBackendApiResponse
	@RideBackendPageableParam
	@PostMapping(value = "/requestor/{requestId}/match", produces = "application/json")
	public PaginationModel<RiderEntity> matchRequestorRequest(@PathVariable Integer requestId,
			@ApiIgnore Pageable pageable) {
		log.info("Entered saveRequestorRequest of RequestController");
		return requestService.getAllMatchingRiders(requestId, pageable);
	}

	@ApiOperation(value = "Get the list of Requestor Requests", response = RiderEntity.class)
	@RideBackendApiResponse
	@RideBackendPageableParam
	@GetMapping(value = "/rider", produces = "application/json")
	public PaginationModel<RiderEntity> getAllRiders(@ApiIgnore Pageable pageable,
			@ApiParam(name = "search", type = "String", value = "Query string for searching by column values", example = "walletId:1,2,3", required = false) @RequestParam(value = "search", required = false) Optional<String> searchString) {
		log.info("Entered getAllRiders of RequestController");
		return requestService.getAllRiders(pageable, searchString);
	}

	@ApiOperation(value = "Create a new Requestor Request", response = RiderEntity.class)
	@RideBackendApiResponse
	@PostMapping(value = "/rider", produces = "application/json")
	public Integer saveRider(@RequestBody RiderEntity riderEntity) {
		log.info("Entered saveRider of RequestController");
		return requestService.saveRider(riderEntity);
	}

}
