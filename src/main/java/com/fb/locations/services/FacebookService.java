package com.fb.locations.services;

import com.fb.locations.model.FacebookPlace;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Facebook service
 */
@Service
public interface FacebookService {

    /**
     * Searches for places using Facebook Graph API and filters it by location passed in parameters.
     * Both country and city has to match exactly ignoring case sensitivity.
     *
     * @param name    searched place
     * @param country searched place location country
     * @param city    searched place location city
     * @return list of found places or empty one if result was not found
     */
    List<FacebookPlace> getPlaces(String name, String country, String city);
}
