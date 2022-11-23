package com.ride.specification;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class SearchCriteria<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String columnName;
    private List<T> values;
    
}