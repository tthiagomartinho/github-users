package br.com.martinho.githubusers.screen.activities.listUsers;

import android.widget.BaseAdapter;

/**
 * Created by Thiago Martinho on 01/05/2016.
 */
public interface IListUsersActivityPresenter {
    boolean loadUsers();

    BaseAdapter createAdapter();

    String retrieveUserProfilePage(int position);
}
