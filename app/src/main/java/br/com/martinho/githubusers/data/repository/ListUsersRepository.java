package br.com.martinho.githubusers.data.repository;

import java.util.List;

import br.com.martinho.githubusers.model.User;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by Thiago on 27/09/2016.
 */

public interface ListUsersRepository {

    Observable<Response<List<User>>> getUsers(int page);
}
