package com.hotelmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * The Class HotelMgmtApplication.
 * 
 * @author Gokulan
 */
@EnableEurekaClient
@SpringBootApplication
public class HotelMgmtApplication {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
	SpringApplication.run(HotelMgmtApplication.class, args);
    }
}