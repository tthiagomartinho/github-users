package br.com.martinho.githubusers.di.module;

import javax.inject.Singleton;

import br.com.martinho.githubusers.data.service.API;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Thiago on 21/09/2016.
 */

@Module
public class ServiceModule {

    @Provides
    @Singleton
    API provideAPI(Retrofit retrofit) {
        return retrofit.create(API.class);
    }
}
