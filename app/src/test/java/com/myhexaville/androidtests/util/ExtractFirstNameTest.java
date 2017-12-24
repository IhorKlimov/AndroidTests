package com.myhexaville.androidtests.util;

import org.junit.Test;

import static com.myhexaville.androidtests.util.FirstNameExtractor.extractFirstName;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class ExtractFirstNameTest {
    private static String DAVE = "Dave";

    @Test
    public void extractFirstName_NullInput_ReturnEmptyString() {
        assertThat(extractFirstName(null), is(""));
    }

    @Test
    public void extractFirstName_EmptyString_ReturnEmptyString() {
        assertThat(extractFirstName(""), is(""));
    }

    @Test
    public void extractFirstName_FullName_ReturnsCorrect() {
        assertThat(extractFirstName("Dave Jones"), is(DAVE));
    }

    @Test
    public void extractFirstName_FullNameAroundWhiteSpaces_ReturnsCorrect() {
        assertThat(extractFirstName("Dave Jones "), is(DAVE));
        assertThat(extractFirstName(" Dave Jones"), is(DAVE));
        assertThat(extractFirstName("Dave   Jones"), is(DAVE));
        assertThat(extractFirstName("   Dave Jones   "), is(DAVE));
        assertThat(extractFirstName(" Dave Jones  "), is(DAVE));
    }

    @Test
    public void extractFirstName_FirstName_ReturnsCorrect() {
        assertThat(extractFirstName("Dave"), is(DAVE));
    }

    @Test
    public void extractFirstName_FirstNameAroundWhiteSpaces_ReturnsCorrect() {
        assertThat(extractFirstName("Dave "), is(DAVE));
        assertThat(extractFirstName(" Dave"), is(DAVE));
        assertThat(extractFirstName(" Dave "), is(DAVE));
        assertThat(extractFirstName("  Dave   "), is(DAVE));
    }

    @Test
    public void extractFirstName_ThreeWords_ReturnsCorrect() {
        assertThat(extractFirstName("Dave Dean Jones"), is(DAVE));
    }

    @Test
    public void extractFirstName_ThreeWordsAroundWhiteSpaces_ReturnsCorrect() {
        assertThat(extractFirstName("Dave Dean Jones "), is(DAVE));
        assertThat(extractFirstName(" Dave Dean Jones"), is(DAVE));
        assertThat(extractFirstName("   Dave Dean Jones   "), is(DAVE));
        assertThat(extractFirstName("Dave   Dean   Jones"), is(DAVE));
        assertThat(extractFirstName("  Dave    Dean   Jones"), is(DAVE));
        assertThat(extractFirstName("  Dave   Dean    Jones"), is(DAVE));
    }
}