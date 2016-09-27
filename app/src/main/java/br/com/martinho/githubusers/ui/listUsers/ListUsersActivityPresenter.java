package br.com.martinho.githubusers.ui.listUsers;

import java.util.List;

import br.com.martinho.githubusers.data.repository.ListUsersRepository;
import br.com.martinho.githubusers.model.User;
import okhttp3.Headers;
import retrofit2.Response;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Thiago Martinho on 30/04/2016.
 */
public class ListUsersActivityPresenter implements ListUsersContract.Presenter {

    private ListUsersRepository repository;
    private ListUsersContract.View view;
    private CompositeSubscription compositeSubscription;
    private int maxPages;

    public ListUsersActivityPresenter(ListUsersRepository repository) {
        this.repository = repository;
        this.compositeSubscription = new CompositeSubscription();
        this.maxPages = 0;
    }

    @Override
    public void setView(ListUsersContract.View view) {
        this.view = view;
    }

    @Override
    public boolean canRequestMore(int page) {
        return page <= this.maxPages;
    }

    @Override
    public void loadUsers(int page) {
        this.view.showLoadingLayout();
        this.compositeSubscription.add(
                this.repository.getUsers(page).concatMap(this::parseHeader).subscribe(users ->
                                this.view.addData(users),
                        throwable -> this.view.showErrorLayout(page)));
    }

    private Observable<List<User>> parseHeader(Response<List<User>> listResponse) {
        Headers headers = listResponse.headers();
        //TODO parse pagination headers
        return Observable.just(listResponse.body());
    }

    @Override
    public void onDestroy() {
        if (this.compositeSubscription != null) {
            this.compositeSubscription.unsubscribe();
        }
        this.view = null;
    }
}
