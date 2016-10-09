package com.example.margo.ver1;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends ActivityInstrumentationTestCase2 {
    public MainActivityTest() {
        super(MainActivity.class);

    }

    @SmallTest
    public void test_edittext() {
        EditText et = (EditText) getActivity().findViewById(R.id.editTextKmPerHour);
        assertNotNull(et);
    }

    @SmallTest
    public void test_edittext2() {
        EditText et = (EditText) getActivity().findViewById(R.id.editTextMetersPerSec);
        assertNotNull(et);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
