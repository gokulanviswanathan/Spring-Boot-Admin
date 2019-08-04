package com.hotelmgmt.util;

import java.util.UUID;

import org.springframework.util.StringUtils;

/**
 * The Class HotelMgmtUtil.
 * 
 * @author Gokulan
 */
public class HotelMgmtUtil {

    /**
     * Check for valid UUID.
     *
     * @param uuidString the uuid string
     * @return true, if successful
     */
    public static boolean checkForValidUUID(String uuidString) {
	if (null != UUID.fromString(uuidString)) {
	    return true;
	}
	return false;
    }

    /**
     * Check for not null and empty.
     *
     * @param param the param
     * @return true, if successful
     */
    public static boolean checkForNotNullAndEmpty(String param) {
	if (!StringUtils.isEmpty(param)) {
	    return true;
	}
	return false;
    }
}
