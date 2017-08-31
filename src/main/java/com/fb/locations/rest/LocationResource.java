package com.fb.locations.rest;

import com.fb.locations.services.FacebookService;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST service for searching places purpose
 */
@Log4j2
@RestController
public class LocationResource {

    @Autowired
    private FacebookService facebookService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public final String getPlaces(
            @RequestParam("name") String name,
            @RequestParam("country") String country,
            @RequestParam("city") String city) {

        log.info("Searching for {} in country: {} and city: {}", name, country, city);
        return new Gson().toJson(facebookService.getPlaces(name, country, city));
    }
}
