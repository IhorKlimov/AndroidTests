package com.myhexaville.androidtests.util;

import com.myhexaville.androidtests.data.Location;

import org.junit.Before;
import org.junit.Test;

import static com.myhexaville.androidtests.util.JsonParser.parseLocationFromJson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParseLocationFromJsonTest {
    private static String CORRECT_CITY_NAME = "Dallas, TX, USA";
    private static double CORRECT_LATITUDE = 32.7766642;
    private static double CORRECT_LONGITUDE = -96.79698789999999;
    private static double NULL_COORDINATE = 0.0;


    // JSON inputs
    private static String correctInput, emptyResults, noCity, noCityAndLocation,
            noGeometry, noLatitude, noLocation, noLongitude, noResults;

    @Before
    public void readJsonFilesToStrings() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        correctInput = FileReaderUtil.readFile(classLoader, "correctInput.json");
        emptyResults = FileReaderUtil.readFile(classLoader, "emptyResults.json");
        noCity = FileReaderUtil.readFile(classLoader, "noCity.json");
        noCityAndLocation = FileReaderUtil.readFile(classLoader, "noCityAndLocation.json");
        noGeometry = FileReaderUtil.readFile(classLoader, "noGeometry.json");
        noLatitude = FileReaderUtil.readFile(classLoader, "noLatitude.json");
        noLongitude = FileReaderUtil.readFile(classLoader, "noLongitude.json");
        noLocation = FileReaderUtil.readFile(classLoader, "noLocation.json");
        noResults = FileReaderUtil.readFile(classLoader, "noResults.json");
    }

    @Test
    public void parseLocationFromJson_NullInput_EmptyLocation() {
        Location location = parseLocationFromJson(null);
        assertThatLocationIsEmpty(location);
    }

    @Test
    public void parseLocationFromJson_EmptyInput_EmptyLocation() {
        Location location = parseLocationFromJson("");
        assertThatLocationIsEmpty(location);
    }

    @Test
    public void parseLocationFromJson_EmptyJson_EmptyLocation() {
        Location location = parseLocationFromJson("{}");
        assertThatLocationIsEmpty(location);
    }

    @Test
    public void parseLocationFromJson_NotAJsonInput_EmptyLocation() {
        Location location = parseLocationFromJson("Some words");
        assertThatLocationIsEmpty(location);
    }

    @Test
    public void parseLocationFromJson_fullInput_CorrectResult() {
        Location location = parseLocationFromJson(correctInput);
        assertThat(location.getCityName(), is(CORRECT_CITY_NAME));
        assertThat(location.getLatitude(), is(CORRECT_LATITUDE));
        assertThat(location.getLongitude(), is(CORRECT_LONGITUDE));
    }

    @Test
    public void parseLocationFromJson_NoResults_EmptyLocation() {
        Location location = parseLocationFromJson(noResults);
        assertThatLocationIsEmpty(location);
    }

    @Test
    public void parseLocationFromJson_EmptyResults_EmptyLocation() {
        Location location = parseLocationFromJson(emptyResults);
        assertThatLocationIsEmpty(location);
    }

    @Test
    public void parseLocationFromJson_NoCityAndLocation_EmptyLocation() {
        Location location = parseLocationFromJson(noCityAndLocation);
        assertThatLocationIsEmpty(location);
    }

    @Test
    public void parseLocationFromJson_NoCity_NullCity() {
        Location location = parseLocationFromJson(noCity);
        assertThat(location.getCityName(), is(nullValue()));
        assertThat(location.getLatitude(), is(CORRECT_LATITUDE));
        assertThat(location.getLongitude(), is(CORRECT_LONGITUDE));
    }

    @Test
    public void parseLocationFromJson_NoLocation_ZeroLatLng() {
        Location location = parseLocationFromJson(noLocation);
        assertThat(location.getCityName(), is(CORRECT_CITY_NAME));
        assertThat(location.getLatitude(), is(NULL_COORDINATE));
        assertThat(location.getLongitude(), is(NULL_COORDINATE));
    }

    @Test
    public void parseLocationFromJson_NoGeometry_ZeroLatLng() {
        Location location = parseLocationFromJson(noGeometry);
        assertThat(location.getCityName(), is(CORRECT_CITY_NAME));
        assertThat(location.getLatitude(), is(NULL_COORDINATE));
        assertThat(location.getLongitude(), is(NULL_COORDINATE));
    }

    @Test
    public void parseLocationFromJson_NoLatitude_ZeroLat() {
        Location location = parseLocationFromJson(noLatitude);
        assertThat(location.getCityName(), is(CORRECT_CITY_NAME));
        assertThat(location.getLatitude(), is(NULL_COORDINATE));
        assertThat(location.getLongitude(), is(CORRECT_LONGITUDE));
    }

    @Test
    public void parseLocationFromJson_NoLongitude_ZeroLon() {
        Location location = parseLocationFromJson(noLongitude);
        assertThat(location.getCityName(), is(CORRECT_CITY_NAME));
        assertThat(location.getLatitude(), is(CORRECT_LATITUDE));
        assertThat(location.getLongitude(), is(NULL_COORDINATE));
    }
    
    

    private void assertThatLocationIsEmpty(Location location) {
        assertThat(location.getCityName(), is(nullValue()));
        assertThat(location.getLatitude(), is(NULL_COORDINATE));
        assertThat(location.getLongitude(), is(NULL_COORDINATE));
    }

}
