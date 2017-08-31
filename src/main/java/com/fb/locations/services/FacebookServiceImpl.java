package com.fb.locations.services;

import com.fb.locations.model.FacebookPlace;
import com.fb.locations.util.FacebookAdapter;
import facebook4j.FacebookException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facebook service implementation
 */
@Log4j2
@Service
public class FacebookServiceImpl implements FacebookService {

    private FacebookAdapter facebookAdapter;

    @Autowired
    public FacebookServiceImpl(FacebookAdapter facebookAdapter) {
        this.facebookAdapter = facebookAdapter;
    }

    /**
     * Searches for places using Facebook Graph API and filters it by location passed in parameters.
     * Both country and city has to match exactly ignoring case sensitivity.
     *
     * @param name    searched place
     * @param country searched place location country
     * @param city    searched place location city
     * @return list of found places or empty one if result was not found
     */
    public List<FacebookPlace> getPlaces(String name, String country, String city) {
        try {
            // make sure none of parameters is empty
            if ("".equals(name) || "".equals(city) || "".equals(country)) {
                throw new FacebookException("One of parameters is empty");
            }

            return facebookAdapter.getFacebook().searchPlaces(name).stream()
                    .filter(p -> p.getLocation() != null && country.equalsIgnoreCase(p.getLocation().getCountry()) &&
                            city.equalsIgnoreCase(p.getLocation().getCity()))
                    .map(p -> new FacebookPlace(p.getName(), p.getLocation().getLatitude(), p.getLocation()
                            .getLongitude()))
                    .collect(Collectors.toList());
        } catch (FacebookException e) {
            log.error("Could not retrieve demanded places", e);
            return Collections.emptyList();
        }
    }

}
