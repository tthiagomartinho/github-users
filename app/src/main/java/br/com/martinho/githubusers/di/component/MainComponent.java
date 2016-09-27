package br.com.martinho.githubusers.di.component;

import javax.inject.Singleton;

import br.com.martinho.githubusers.di.module.ApplicationModule;
import br.com.martinho.githubusers.di.module.NetworkModule;
import br.com.martinho.githubusers.di.module.RepositoryModule;
import br.com.martinho.githubusers.di.module.ServiceModule;
import dagger.Component;

/**
 * Created by Thiago on 21/09/2016.
 */


@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        ServiceModule.class,
        RepositoryModule.class
})
public interface MainComponent {

    UiComponent uiComponent();
}
