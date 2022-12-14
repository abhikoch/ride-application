package com.ride.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ride.entity.RiderEntity;

@Repository
public interface RiderRepo extends JpaRepository<RiderEntity, Integer>, JpaSpecificationExecutor<RiderEntity> {
	Page<RiderEntity> findAllByFromLocationAndToLocation(String fromLocation, String toLocation, Pageable page);
}