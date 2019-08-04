package com.hotelmgmt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

/**
 * The Class Hotel.
 * 
 * @author Gokulan
 */
@Entity
@Table(name = "hotel")
@FilterDef(name = "filterByCity", parameters = { @ParamDef(name = "city", type = "string") })
public class Hotel implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * The name.
     */
    @Column(name = "name")
    private String name;

    /**
     * The city.
     */
    @Column(name = "city")
    private String city;

    /**
     * The state.
     */
    @Column(name = "state")
    private String state;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
	return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(String id) {
	this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    public String getCity() {
	return city;
    }

    /**
     * Sets the city.
     *
     * @param city the new city
     */
    public void setCity(String city) {
	this.city = city;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public String getState() {
	return state;
    }

    /**
     * Sets the state.
     *
     * @param state the new state
     */
    public void setState(String state) {
	this.state = state;
    }
}