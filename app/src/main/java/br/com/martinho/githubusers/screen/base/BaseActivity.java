package br.com.martinho.githubusers.screen.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import br.com.martinho.githubusers.application.App;

/**
 * Created by Thiago on 27/07/2016.
 */
public class BaseActivity extends AppCompatActivity {

    public App retrieveApplication() {
        return (App) getApplication();
    }

    public Context retrieveContext() {
        return this;
    }
}
