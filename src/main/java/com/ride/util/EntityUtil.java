package com.ride.util;

import java.util.Arrays;

import com.ride.specification.SearchCriteria;

public class EntityUtil {
	
	public static SearchCriteria<String> buildSearchCriterionFromSearchString(String searchString) {
		SearchCriteria<String> searchCriterion = new SearchCriteria<>();
		String[] searchStringArray = searchString.split(":");
		if (searchStringArray.length != 2) {
			throw new RuntimeException("Invalid Query");
		}
		searchCriterion.setColumnName(searchStringArray[0]);
		searchCriterion.setValues(Arrays.asList(searchStringArray[1].split(",")));
		return searchCriterion;
	}
	
}