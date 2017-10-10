package com.jersey.series.spring.hibernate.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by laurenra on 10/9/17.
 */
@Component
@Path("/")
public class CheckService {

    private static final Logger log = LoggerFactory.getLogger(CheckService.class);

    @GET
    @Path("/check")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFox() {
        log.info("Web application health check.");
        return "The web application is alive.";
    }
}
