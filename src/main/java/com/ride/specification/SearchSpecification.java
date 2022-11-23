package com.ride.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class SearchSpecification<T, U> implements Specification<T> {

	private static final long serialVersionUID = 1L;
	
	private SearchCriteria<U> criteria;

    public SearchSpecification(SearchCriteria<U> criteria){
        this.criteria =criteria;
    }

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return builder.in(root.get(criteria.getColumnName())).value(criteria.getValues());
	}

}