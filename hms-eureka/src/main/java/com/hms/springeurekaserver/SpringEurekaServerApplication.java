package com.hms.springeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * The Class SpringEurekaServerApplication.
 * 
 * @author Gokulan
 */
@EnableEurekaServer
@EnableAdminServer
@SpringBootApplication
public class SpringEurekaServerApplication {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
	SpringApplication.run(SpringEurekaServerApplication.class, args);
    }
}
