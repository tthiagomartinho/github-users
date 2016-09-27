package br.com.martinho.githubusers;

import android.app.Application;

import br.com.martinho.githubusers.di.component.DaggerMainComponent;
import br.com.martinho.githubusers.di.component.MainComponent;

/**
 * Created by Thiago Martinho on 27/07/2016.
 */
public class App extends Application {

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        dependencyInjection();
    }

    private void dependencyInjection() {
        mainComponent = DaggerMainComponent.create();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
