package br.com.martinho.githubusers.di.component;

import br.com.martinho.githubusers.di.module.PresenterModule;
import br.com.martinho.githubusers.di.scope.PerActivity;
import br.com.martinho.githubusers.ui.listUsers.ListUsersActivity;
import dagger.Subcomponent;

/**
 * Created by Thiago on 21/09/2016.
 */

@PerActivity
@Subcomponent(modules = {PresenterModule.class})
public interface UiComponent {
    void inject(ListUsersActivity listUsersActivity);

}
