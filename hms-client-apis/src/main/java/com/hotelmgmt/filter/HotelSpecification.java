package com.hotelmgmt.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hotelmgmt.entity.Hotel;

/**
 * The Class HotelSpecification.
 * 
 * @author Gokulan
 */
public class HotelSpecification implements Specification<Hotel> {

    /**
     * The criteria.
     */
    private SearchCriteria criteria;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.jpa.domain.Specification#toPredicate(javax.
     * persistence.criteria.Root, javax.persistence.criteria.CriteriaQuery,
     * javax.persistence.criteria.CriteriaBuilder)
     */
    @Override
    public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

	if (criteria.getOperation().equalsIgnoreCase(">")) {
	    return builder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
	} else if (criteria.getOperation().equalsIgnoreCase("<")) {
	    return builder.lessThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
	} else if (criteria.getOperation().equalsIgnoreCase(":")) {
	    if (root.get(criteria.getKey()).getJavaType() == String.class) {
		return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
	    } else {
		return builder.equal(root.get(criteria.getKey()), criteria.getValue());
	    }
	}
	return null;
    }

    /**
     * Instantiates a new hotel specification.
     *
     * @param criteria the criteria
     */
    public HotelSpecification(SearchCriteria criteria) {
	super();
	this.criteria = criteria;
    }
}