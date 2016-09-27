package br.com.martinho.githubusers.di.module;

import br.com.martinho.githubusers.data.repository.ListUsersRepository;
import br.com.martinho.githubusers.data.repository.impl.ListUsersRepositoryImpl;
import br.com.martinho.githubusers.data.service.API;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Thiago on 21/09/2016.
 */

@Module
public class RepositoryModule {
    @Provides
    ListUsersRepository provideListUsersRepository(API api) {
        return new ListUsersRepositoryImpl(api);
    }
}
