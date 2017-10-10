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
@Path("/test")
public class FoxResource {

    private static final Logger log = LoggerFactory.getLogger(FoxResource.class);

    @GET
    @Path("/fox")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFox() {
        log.info("Get a fox...");
        return "got a fox.";
    }
}
