package com.ride.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ride.entity.RequestorEntity;
import com.ride.entity.RiderEntity;
import com.ride.enumeration.Status;
import com.ride.model.PaginationModel;
import com.ride.repository.RequestorRepo;
import com.ride.repository.RiderRepo;
import com.ride.specification.SearchCriteria;
import com.ride.specification.SearchSpecification;
import com.ride.util.EntityUtil;

/**
 * @author Kochhar, Abhinav
 *
 */
@Service
public class RequestService {
	
	@Autowired
	private RequestorRepo requestorRepo;
	
	@Autowired
	private RiderRepo riderRepo;

	public List<RequestorEntity> saveAllRequestorRequest(List<RequestorEntity> requestorEntityList) {
		return requestorRepo.saveAll(requestorEntityList);
	}
  
	public PaginationModel<RequestorEntity> getAllRequestorRequests(Pageable pageable, Optional<String> searchString) {
		Page<RequestorEntity> page = null;
		if(searchString.isPresent())
			page = requestorRepo.findAll(fetchSearchSpecification(searchString.get()), pageable);
		else
			page = requestorRepo.findAll(pageable);
		PaginationModel<RequestorEntity> requestorPage = new PaginationModel<>();
		requestorPage.setCount(page.getTotalElements());
		requestorPage.setValues(page.getContent());
		return requestorPage;
	}
	
	private <T> SearchSpecification<T, String> fetchSearchSpecification(String searchString) {
		SearchCriteria<String> searchCriterion = EntityUtil.buildSearchCriterionFromSearchString(searchString);
		return new SearchSpecification<>(searchCriterion);
	}

	public RequestorEntity getRequestorRequest(Integer requestorId) {
		return requestorRepo.findById(requestorId).orElseThrow(() -> new RuntimeException("NOT FOUND"));
	}

	public Integer saveRequestorRequest(RequestorEntity requestorEntity) {
		requestorEntity.setStatus(Status.PENDING);
		return requestorRepo.save(requestorEntity).getId();
	}
	
	public PaginationModel<RiderEntity> getAllMatchingRiders(Integer requestorId, Pageable pageable) {
		RequestorEntity requestorRequest = getRequestorRequest(requestorId);		
		Page<RiderEntity> page = riderRepo.findAllByFromLocationAndToLocation(requestorRequest.getFromLocation(), requestorRequest.getToLocation(), pageable);
		PaginationModel<RiderEntity> riderPage = new PaginationModel<>();
		riderPage.setCount(page.getTotalElements());
		riderPage.setValues(page.getContent());
		return riderPage;
	}
	
	
	public List<RiderEntity> saveAllRider(List<RiderEntity> riderEntity) {
		return riderRepo.saveAll(riderEntity);
	}
  
	public PaginationModel<RiderEntity> getAllRiders(Pageable pageable, Optional<String> searchString) {
		Page<RiderEntity> page = null;
		if(searchString.isPresent())
			page = riderRepo.findAll(fetchSearchSpecification(searchString.get()), pageable);
		else
			page = riderRepo.findAll(pageable);
		PaginationModel<RiderEntity> riderPage = new PaginationModel<>();
		riderPage.setCount(page.getTotalElements());
		riderPage.setValues(page.getContent());
		return riderPage;
	}

	public RiderEntity getRider(Integer riderId) {
		return riderRepo.findById(riderId).orElseThrow(() -> new RuntimeException("NOT FOUND"));
	}

	public Integer saveRider(RiderEntity riderEntity) {
		return riderRepo.save(riderEntity).getId();
	}
	
}
