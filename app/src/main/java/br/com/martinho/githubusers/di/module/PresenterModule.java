package br.com.martinho.githubusers.di.module;

import br.com.martinho.githubusers.data.repository.ListUsersRepository;
import br.com.martinho.githubusers.di.scope.PerActivity;
import br.com.martinho.githubusers.ui.listUsers.ListUsersActivityPresenter;
import br.com.martinho.githubusers.ui.listUsers.ListUsersContract;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Thiago on 21/09/2016.
 */

@Module
public class PresenterModule {

    @PerActivity
    @Provides
    ListUsersContract.Presenter provideListUserPresenter(ListUsersRepository repository) {
        return new ListUsersActivityPresenter(repository);
    }
}
