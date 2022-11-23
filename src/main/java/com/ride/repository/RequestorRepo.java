package com.ride.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ride.entity.RequestorEntity;

@Repository
public interface RequestorRepo extends JpaRepository<RequestorEntity, Integer>, JpaSpecificationExecutor<RequestorEntity> {
	
}