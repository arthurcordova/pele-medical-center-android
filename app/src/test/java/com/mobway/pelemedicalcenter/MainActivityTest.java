package com.mobway.pelemedicalcenter;

import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.*;

/**
 * Created by arthur.stapassoli on 17/11/2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {

    MainActivity activity;

    @Before
    public void setUp(){
        activity= Robolectric.setupActivity(MainActivity.class);
    }


    @Test
    public void readStringFromContext_LocalizedString() {
        assertEquals(activity.getStringTest(), "MUhaha");
    }

    @Test
    public void test_image_logo() {
        ImageView imgLogo = activity.findViewById(R.id.img_logo);
        assertNotNull(imgLogo);
        assertTrue(imgLogo.getId() == R.id.img_logo);
    }


}
