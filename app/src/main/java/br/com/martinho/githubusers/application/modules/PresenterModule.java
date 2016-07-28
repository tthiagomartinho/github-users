package br.com.martinho.githubusers.application.modules;

import android.support.design.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import br.com.martinho.githubusers.network.API;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Thiago Martinho on 27/07/2016.
 */

@Module
public class PresenterModule {

    public static String mBaseUrl = "https://api.github.com/";

    public PresenterModule() {
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .followRedirects(false)
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    API provideAPI(Retrofit retrofit) {
        return retrofit.create(API.class);
    }
}
