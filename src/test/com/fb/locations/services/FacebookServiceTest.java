package com.fb.locations.services;

import com.fb.locations.model.FacebookPlace;
import com.fb.locations.util.FacebookAdapter;
import facebook4j.FacebookException;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link FacebookService}
 */
@Log4j2
@RunWith(MockitoJUnitRunner.class)
public class FacebookServiceTest {

    private FacebookService facebookService;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private FacebookAdapter facebookAdapter;


    @Before
    public void setUp() {
        facebookService = new FacebookServiceImpl(facebookAdapter);

    }

    @Test
    public void shouldReturnEmptyListBasedOnEmptyParameters() throws FacebookException {
        // given
        String empty = "";

        // when
        final List<FacebookPlace> places = facebookService.getPlaces(empty, empty, empty);

        // then
        verifyZeroInteractions(facebookAdapter);
        assertThat(places).isEmpty();
    }

    @Test
    public void shouldReturnNonEmptyListBasedOnNonEmptyParameters() throws FacebookException {
        // given
        final FacebookPlace facebookPlace = new FacebookPlace("Something", 54.112, 21.123);
        when(facebookAdapter.getFacebook().searchPlaces(any()).stream().filter(any()).map(any()).collect(any()))
                .thenReturn(Lists.newArrayList(facebookPlace));

        // when
        final List<FacebookPlace> places = facebookService.getPlaces("Something", "Poland", "Warsaw");

        // then
        verify(facebookAdapter, times(2)).getFacebook();
        assertThat(places).contains(facebookPlace).hasSize(1);
    }
}
