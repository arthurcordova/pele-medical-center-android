package com.mobway.pelemedicalcenter;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadow.api.Shadow;
import org.robolectric.shadows.ShadowDrawable;

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
    public void test_image_logo() {
        ImageView imgLogo = activity.findViewById(R.id.img_logo);
        int drawableID = Shadows.shadowOf(imgLogo.getDrawable()).getCreatedFromResId();

        assertNotNull(imgLogo);
        assertEquals(imgLogo.getScaleType(), ImageView.ScaleType.CENTER_INSIDE);
        assertNotNull(imgLogo.getDrawable());
        assertEquals(drawableID, R.drawable.logo);
    }


}
