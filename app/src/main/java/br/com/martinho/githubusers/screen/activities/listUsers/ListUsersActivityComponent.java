package br.com.martinho.githubusers.screen.activities.listUsers;

import br.com.martinho.githubusers.application.components.ApplicationComponent;
import br.com.martinho.githubusers.application.scopes.PerActivity;
import dagger.Component;

/**
 * Created by Thiago Martinho on 30/04/2016.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ListUsersActivityModule.class)
public interface ListUsersActivityComponent {

    void inject(ListUsersActivity listUsersActivity);
}
