package br.com.martinho.githubusers.screen.activities.listUsers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.martinho.githubusers.R;
import br.com.martinho.githubusers.application.App;
import br.com.martinho.githubusers.screen.activities.listUsers.IListUsersActivity;

public class ListUsersActivity extends AppCompatActivity implements IListUsersActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
    }

    @Override
    public App retrieveApplication() {
        return (App) getApplication();
    }
}
