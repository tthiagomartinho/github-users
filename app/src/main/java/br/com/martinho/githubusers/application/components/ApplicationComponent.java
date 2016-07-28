package br.com.martinho.githubusers.application.components;

import javax.inject.Singleton;

import br.com.martinho.githubusers.application.modules.ApplicationModule;
import br.com.martinho.githubusers.screen.activities.listUsers.ListUsersActivity;
import dagger.Component;

/**
 * Created by Thiago Martinho on 27/07/2016.
 */

@Singleton
@Component(
        modules = {
                ApplicationModule.class
        }
)
public interface ApplicationComponent {
    void inject(ListUsersActivity baseActivity);
}
