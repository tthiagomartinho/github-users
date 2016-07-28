package br.com.martinho.githubusers.application;

import android.app.Application;

import br.com.martinho.githubusers.application.components.ApplicationComponent;
import br.com.martinho.githubusers.application.components.DaggerApplicationComponent;
import br.com.martinho.githubusers.application.components.DaggerPresenterComponent;
import br.com.martinho.githubusers.application.components.PresenterComponent;
import br.com.martinho.githubusers.application.modules.ApplicationModule;
import br.com.martinho.githubusers.application.modules.PresenterModule;

/**
 * Created by Thiago Martinho on 27/07/2016.
 */
public class App extends Application {

    private ApplicationComponent applicationComponent;

    private PresenterComponent mPresenterComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mPresenterComponent = DaggerPresenterComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .presenterModule(new PresenterModule())
                .build();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public PresenterComponent getPresenterComponent() {
        return mPresenterComponent;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
