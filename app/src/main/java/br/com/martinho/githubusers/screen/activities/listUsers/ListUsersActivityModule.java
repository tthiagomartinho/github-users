package br.com.martinho.githubusers.screen.activities.listUsers;

import br.com.martinho.githubusers.application.scopes.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Thiago Martinho on 30/04/2016.
 */

@Module
public class ListUsersActivityModule {

    private ListUsersActivity listUsersActivity;

    public ListUsersActivityModule(ListUsersActivity listUsersActivity) {
        this.listUsersActivity = listUsersActivity;
    }

    @Provides
    @PerActivity
    ListUsersActivity provideListUsersActivity() {
        return listUsersActivity;
    }

    @Provides
    @PerActivity
    IListUsersActivityPresenter provideListUsersActivityPresenter() {
        return new ListUsersActivityPresenter(listUsersActivity);
    }
}
