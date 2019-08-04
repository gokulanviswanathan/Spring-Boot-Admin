package com.hotelmgmt.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotelmgmt.constants.HotelMgmtConstants;
import com.hotelmgmt.entity.Hotel;
import com.hotelmgmt.entity.HotelRequest;
import com.hotelmgmt.exception.HotelMgmtException;
import com.hotelmgmt.filter.HotelSpecification;
import com.hotelmgmt.filter.SearchCriteria;
import com.hotelmgmt.repository.HotelRepository;
import com.hotelmgmt.util.HotelMgmtUtil;

/**
 * The Class HotelMgmtController.
 * 
 * @author Gokulan
 */
@Controller
@RequestMapping("hotel")
public class HotelMgmtController {

    /**
     * The hotel repository.
     */
    @Autowired
    HotelRepository hotelRepository;

    /**
     * Gets the hotels.
     *
     * @param pageable the pageable
     * @return the hotels
     */
    @GetMapping("getHotels")
    @ResponseBody
    public Object getHotels(Pageable pageable) {
	return hotelRepository.findAll(pageable);
    }

    /**
     * Adds the hotel.
     *
     * @param hotelRequest the hotel request
     * @return the string
     */
    @PostMapping("addHotel")
    @ResponseBody
    public String addHotel(@RequestBody HotelRequest hotelRequest) {

	Hotel hotel = new Hotel();
	hotel.setId(UUID.randomUUID().toString());
	hotel.setName(hotelRequest.getName());
	hotel.setCity(hotelRequest.getCity());
	hotel.setState(hotelRequest.getState());

	hotelRepository.saveAndFlush(hotel);

	return hotel.getId() + HotelMgmtConstants.ADD_SUCCESS;
    }

    /**
     * Delete hotel.
     *
     * @param hotelId the hotel id
     * @return the string
     */
    @DeleteMapping("/{hotelId}/deleteHotel")
    @ResponseBody
    public String deleteHotel(@PathVariable("hotelId") String hotelId) {
	if (StringUtils.isEmpty(hotelId)) {
	    throw new HotelMgmtException();
	}

	if (HotelMgmtUtil.checkForValidUUID(hotelId)) {
	    hotelRepository.deleteById(hotelId);
	}

	return hotelId + HotelMgmtConstants.DELETE_SUCCESS;
    }

    /**
     * Patch hotel.
     *
     * @param hotelId      the hotel id
     * @param hotelRequest the hotel request
     * @return the string
     */
    @PatchMapping("/{hotelId}/patchHotel")
    @ResponseBody
    public String patchHotel(@PathVariable("hotelId") String hotelId, @RequestBody HotelRequest hotelRequest) {
	if (StringUtils.isEmpty(hotelId)) {
	    throw new HotelMgmtException();
	}

	if (HotelMgmtUtil.checkForValidUUID(hotelId)) {
	    Optional<Hotel> existingHotelOpt = hotelRepository.findById(hotelId);
	    if (existingHotelOpt.isPresent()) {
		Hotel existingHotel = existingHotelOpt.get();
		if (null != existingHotel)
		    if (HotelMgmtUtil.checkForNotNullAndEmpty(hotelRequest.getName())) {
			existingHotel.setName(hotelRequest.getName());
		    }

		if (HotelMgmtUtil.checkForNotNullAndEmpty(hotelRequest.getCity())) {
		    existingHotel.setCity(hotelRequest.getCity());
		}

		if (HotelMgmtUtil.checkForNotNullAndEmpty(hotelRequest.getState())) {
		    existingHotel.setState(hotelRequest.getState());
		}

		hotelRepository.saveAndFlush(existingHotel);
	    }

	}

	return hotelId + HotelMgmtConstants.PATCH_SUCCESS;
    }

    /**
     * Gets the hotels with filter.
     *
     * @param filterValue the filter value
     * @param pageable    the pageable
     * @return the hotels with filter
     */
    @GetMapping("getHotelsWithFilter")
    @ResponseBody
    public Object getHotelsWithFilter(@RequestParam(value = "filterBy") String filterValue, Pageable pageable) {
	SearchCriteria searchCriteria = new SearchCriteria(HotelMgmtConstants.FILTER_COLUMN,
		HotelMgmtConstants.FILTER_OPERATION, filterValue);
	HotelSpecification spec = new HotelSpecification(searchCriteria);

	return hotelRepository.findAll(spec, pageable);
    }

    /**
     * Gets the hotels with multiple filter.
     *
     * @param cities   the cities
     * @param pageable the pageable
     * @return the hotels with multiple filter
     */
    @GetMapping("getHotelsWithMultipleFilter")
    @ResponseBody
    public Object getHotelsWithMultipleFilter(@RequestParam("city") String[] cities, Pageable pageable) {

	if (null != cities) {

	    List<String> citiesList = Arrays.asList(cities);

	    return hotelRepository.findByCityIn(citiesList, pageable);

	}

	return null;
    }
}