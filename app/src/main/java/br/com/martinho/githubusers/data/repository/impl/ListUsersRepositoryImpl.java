package br.com.martinho.githubusers.data.repository.impl;

import java.util.List;

import br.com.martinho.githubusers.data.repository.ListUsersRepository;
import br.com.martinho.githubusers.data.service.API;
import br.com.martinho.githubusers.model.User;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Thiago on 27/09/2016.
 */

public class ListUsersRepositoryImpl implements ListUsersRepository {

    private API api;

    public ListUsersRepositoryImpl(API api) {
        this.api = api;
    }

    @Override
    public Observable<Response<List<User>>> getUsers(int page) {
        return this.api.retrieveUsers(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
