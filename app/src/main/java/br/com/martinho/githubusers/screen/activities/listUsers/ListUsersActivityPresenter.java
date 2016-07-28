package br.com.martinho.githubusers.screen.activities.listUsers;

import java.util.List;

import br.com.martinho.githubusers.model.User;
import br.com.martinho.githubusers.screen.adapters.ListUsersAdapter;
import br.com.martinho.githubusers.screen.base.BaseActivityPresenter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Thiago Martinho on 30/04/2016.
 */
public class ListUsersActivityPresenter extends BaseActivityPresenter implements IListUsersActivityPresenter {

    private IListUsersActivity listUsersActivity;

    public ListUsersActivityPresenter(IListUsersActivity listUsersActivity) {
        this.listUsersActivity = listUsersActivity;
        listUsersActivity.retrieveApplication().getPresenterComponent().inject(this);
    }

    @Override
    public void loadUsers() {
        api.retrieveUsers(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::createAdapter);
    }

    private void createAdapter(List<User> users) {
        ListUsersAdapter listUsersAdapter = new ListUsersAdapter(listUsersActivity.retrieveContext(), users);
        listUsersActivity.onUsersLoaded(listUsersAdapter);
    }
}
