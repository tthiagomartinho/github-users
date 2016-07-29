package br.com.martinho.githubusers.screen.activities.listUsers;

import android.content.Context;

import br.com.martinho.githubusers.application.App;
import br.com.martinho.githubusers.screen.adapters.ListUsersAdapter;

/**
 * Created by Thiago Martinho on 01/05/2016.
 */
public interface IListUsersActivity {

    App retrieveApplication();

    Context retrieveContext();
}
