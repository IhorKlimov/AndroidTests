package com.myhexaville.androidtests.chat;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.myhexaville.androidtests.R;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.victoralbertos.device_animation_test_rule.DeviceAnimationTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.linkedin.android.testbutler.TestButler.verifyAnimationsDisabled;
import static com.myhexaville.androidtests.util.AnimationAssertions.verifyAnimationsDisabled;
import static com.myhexaville.androidtests.util.Matchers.withItemText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChatActivityLargeTest {
    private static final String MESSAGE = "Some message";

    @ClassRule
    public static DeviceAnimationTestRule
            deviceAnimationTestRule = new DeviceAnimationTestRule();

    @Rule
    public ActivityTestRule<ChatActivity> activityRule =
            new ActivityTestRule<>(ChatActivity.class);

    @Before
    public void checkAnimations(){
        // unnecessary, just to double check the correct implementation of DeviceAnimationTestRule library
        verifyAnimationsDisabled(activityRule.getActivity());
    }

    @Test
    public void sendNormalMessage_MessageAdded() {
        onView(withId(R.id.message_input)).perform(typeText(MESSAGE), closeSoftKeyboard());
        onView(withId(R.id.send_button)).perform(click());
        onView(withId(R.id.list)).perform(scrollTo(hasDescendant(withText(MESSAGE))));
        onView(withItemText(MESSAGE)).check(matches(isDisplayed()));
    }


}
