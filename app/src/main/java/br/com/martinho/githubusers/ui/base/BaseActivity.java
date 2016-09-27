package br.com.martinho.githubusers.ui.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import br.com.martinho.githubusers.App;
import br.com.martinho.githubusers.di.component.UiComponent;

/**
 * Created by Thiago on 27/07/2016.
 */
public class BaseActivity extends AppCompatActivity {

    private UiComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUiComponent();
    }

    private void initUiComponent() {
        component = ((App) getApplication()).getMainComponent().uiComponent();
    }

    protected UiComponent getUiComponent() {
        return component;
    }

    protected boolean isConnectedToInternet() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}
