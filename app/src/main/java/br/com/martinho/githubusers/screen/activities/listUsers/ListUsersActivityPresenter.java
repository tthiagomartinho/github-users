package br.com.martinho.githubusers.screen.activities.listUsers;

import br.com.martinho.githubusers.screen.base.BaseActivityPresenter;

/**
 * Created by Thiago Martinho on 30/04/2016.
 */
public class ListUsersActivityPresenter extends BaseActivityPresenter implements IListUsersActivityPresenter {

    private IListUsersActivity listUsersActivity;


    public ListUsersActivityPresenter(IListUsersActivity listUsersActivity) {
        this.listUsersActivity = listUsersActivity;
        listUsersActivity.retrieveApplication().getPresenterComponent().inject(this);
    }
}
