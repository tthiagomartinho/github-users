package br.com.martinho.githubusers.screen.activities.listUsers;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import br.com.martinho.githubusers.BuildConfig;
import br.com.martinho.githubusers.R;
import br.com.martinho.githubusers.ui.listUsers.ListUsersActivity;

import static org.junit.Assert.assertNotNull;

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
}