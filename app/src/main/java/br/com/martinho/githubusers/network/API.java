package br.com.martinho.githubusers.network;

import java.util.List;

import br.com.martinho.githubusers.model.User;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Thiago Martinho on 27/07/2016.
 */
public interface API {

    @GET("users")
    Observable<List<User>> retrieveUsers(@Query("since") int page);
}
