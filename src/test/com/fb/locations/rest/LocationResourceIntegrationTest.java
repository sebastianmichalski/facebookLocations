package com.fb.locations.rest;


import com.fb.locations.LocationsApplication;
import com.fb.locations.model.FacebookPlace;
import com.fb.locations.services.FacebookService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Integration tests for {@link LocationResource}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LocationsApplication.class)
@WebAppConfiguration
public class LocationResourceIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private LocationResource locationResource;

    @Mock
    private FacebookService facebookService;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();

        Whitebox.setInternalState(locationResource, "facebookService", facebookService);
    }

    @Test
    public void shouldReturnEmptyJsonBasedOnEmptyParameters() throws Exception {
        mvc.perform(get("/").param("name", "").param("city", "").param("country", ""))
                .andExpect(mvcResult -> mvcResult
                        .getResponse().getContentAsString().equals("[]"));

    }

    @Test
    public void shouldParseResponseProperlyBasedOnNonEmptyParameters() throws Exception {
        // given
        when(facebookService.getPlaces("Egnyte", "Poland", "Poznan")).thenReturn(Lists.newArrayList(
                new FacebookPlace("Egnyte", 52.404167557908, 16.940044275923)));

        // then
        mvc.perform(get("/").param("name", "Egnyte").param("city", "Poznan").param("country", "Poland"))
                .andExpect(mvcResult -> mvcResult
                        .getResponse().getContentAsString().equals("[{\"name\":\"Egnyte\"," +
                                "\"latitude\":52.404167557908,\"longitude\":16.940044275923}]"));

    }
}
