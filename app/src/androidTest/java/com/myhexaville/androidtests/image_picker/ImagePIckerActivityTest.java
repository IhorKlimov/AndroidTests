package com.myhexaville.androidtests.image_picker;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.myhexaville.androidtests.MainActivity;
import com.myhexaville.androidtests.R;
import com.theartofdev.edmodo.cropper.CropImage;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import io.victoralbertos.device_animation_test_rule.DeviceAnimationTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.myhexaville.androidtests.common.Constants.IMAGE_URL;
import static com.myhexaville.androidtests.util.Matchers.hasDrawable;
import static com.myhexaville.androidtests.util.Matchers.withTag;
import static com.theartofdev.edmodo.cropper.CropImage.CROP_IMAGE_EXTRA_RESULT;
import static com.theartofdev.edmodo.cropper.CropImage.CROP_IMAGE_EXTRA_SOURCE;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.not;

public class ImagePIckerActivityTest {

    @Rule
    public IntentsTestRule<ImagePickerActivity> activityRule =
            new IntentsTestRule<>(ImagePickerActivity.class);

    @ClassRule
    public static DeviceAnimationTestRule deviceAnimationTestRule = new DeviceAnimationTestRule();


    @Before
    public void setUp() {
        savePickedImage();
        intending(hasAction(Intent.ACTION_CHOOSER)).respondWith(getCroppedImageResult());
    }

    @Test
    public void default_ImageHasNoDrawable(){
        onView(withId(R.id.image)).check(matches(not(hasDrawable())));
    }

    @Test
    public void pickImage_ImagePicked(){
        onView(withId(R.id.camera)).perform(click());
        onView(withId(R.id.image)).check(matches(hasDrawable()));
    }
    
    @Test
    public void correctImageShouldBeLoaded(){
        onView(withId(R.id.second_image)).check(matches(withTag(IMAGE_URL)));
    }

    private Instrumentation.ActivityResult getCroppedImageResult() {
        Intent resultData = new Intent();
        File dir = activityRule.getActivity().getExternalCacheDir();
        File file = new File(dir.getPath(), "pickImageResult.jpeg");
        Uri uri = Uri.fromFile(file);
        resultData.putExtra(CROP_IMAGE_EXTRA_RESULT, new CropImage.ActivityResult(null, uri, null, null, null, 0, 1));
        return new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
    }

    private void savePickedImage() {
        Bitmap bm = BitmapFactory.decodeResource(activityRule.getActivity().getResources(), R.drawable.android);
        assertTrue(bm != null);
        File dir = activityRule.getActivity().getExternalCacheDir();
        File file = new File(dir.getPath(), "pickImageResult.jpeg");
        System.out.println(file.getAbsolutePath());
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
