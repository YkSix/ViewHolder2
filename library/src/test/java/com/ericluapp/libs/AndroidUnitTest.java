package com.ericluapp.libs;

import android.app.Activity;
import android.app.Application;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class AndroidUnitTest {

    @Test
    public void dummyUnitTestWithMockito() {
        Activity activity = mock(Activity.class);
        assertThat(activity, notNullValue());
        Application app = mock(Application.class);
        when(activity.getApplication()).thenReturn(app);
        assertThat(app, is(equalTo(activity.getApplication())));

        verify(activity).getApplication();
        verifyNoMoreInteractions(activity);
    }
}