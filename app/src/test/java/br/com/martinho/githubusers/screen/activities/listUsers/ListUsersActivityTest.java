package br.com.martinho.githubusers.screen.activities.listUsers;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.BaseAdapter;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import br.com.martinho.githubusers.BuildConfig;
import br.com.martinho.githubusers.R;
import br.com.martinho.githubusers.screen.adapters.ListUsersAdapter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

/**
 * Created by Thiago on 30/07/2016.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class ListUsersActivityTest {
    private ListUsersActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(ListUsersActivity.class);
    }

    @Test
    public void checkComponents() throws Exception {
        AppBarLayout appBarLayout = (AppBarLayout) activity.findViewById(R.id.activity_list_users_appBarLayout);
        assertNotNull("AppBarLayout could not be found", appBarLayout);

        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.activity_list_users_toolbar);
        assertNotNull("Toolbar could not be found", toolbar);

        ListView usersList = (ListView) activity.findViewById(R.id.activity_list_users_data);
        assertNotNull("ListView could not be found", usersList);
    }

    @Test
    public void checkAdapter() throws Exception {
        BaseAdapter adapterExpected = new ListUsersAdapter(activity);
        assertNotSame(activity.getUsersList().getAdapter(), adapterExpected);
    }

    @Test
    public void selectUserAndStartBrowserActivity() throws Exception {
        Thread.sleep(3000); //wait for the api to respond

        Intent expectedIntent = new Intent(Intent.ACTION_VIEW);

        ListView usersList = (ListView) activity.findViewById(R.id.activity_list_users_data);
        usersList.performItemClick(usersList.getAdapter().getView(0, null, null), 0, usersList.getAdapter().getItemId(0));

        ShadowActivity shadowMainActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowMainActivity.getNextStartedActivity();

        Uri uri = actualIntent.getData();
        expectedIntent.setData(uri); //since the data comes from the API, we have to set the URI to the expected intent.

        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}