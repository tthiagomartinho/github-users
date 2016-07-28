package br.com.martinho.githubusers.application.components;

import javax.inject.Singleton;

import br.com.martinho.githubusers.application.modules.ApplicationModule;
import br.com.martinho.githubusers.application.modules.PresenterModule;
import br.com.martinho.githubusers.screen.base.BaseActivityPresenter;
import dagger.Component;

/**
 * Created by Thiago Martinho on 27/07/2016.
 */

@Singleton
@Component(
        modules = {
                PresenterModule.class, ApplicationModule.class
        }
)
public interface PresenterComponent {
    void inject(BaseActivityPresenter baseActivityPresenter);
}
