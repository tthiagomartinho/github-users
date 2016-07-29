package br.com.martinho.githubusers.screen.activities.listUsers;

import android.text.TextUtils;
import android.widget.BaseAdapter;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import br.com.martinho.githubusers.model.User;
import br.com.martinho.githubusers.screen.adapters.ListUsersAdapter;
import br.com.martinho.githubusers.screen.base.BaseActivityPresenter;
import br.com.martinho.githubusers.util.Constants;
import okhttp3.Headers;
import retrofit2.Response;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Thiago Martinho on 30/04/2016.
 */
public class ListUsersActivityPresenter extends BaseActivityPresenter implements IListUsersActivityPresenter {

    private IListUsersActivity listUsersActivity;
    private ListUsersAdapter listUsersAdapter;
    private List<User> users;
    private boolean canRequestMoreUsers;

    private int sinceGitHubUsers;
    private Subscription subscribe;

    public ListUsersActivityPresenter(IListUsersActivity listUsersActivity) {
        this.listUsersActivity = listUsersActivity;
        this.users = new ArrayList<>();
        this.sinceGitHubUsers = 1;
        this.canRequestMoreUsers = true;
        listUsersActivity.retrieveApplication().getPresenterComponent().inject(this);
    }

    @Override
    public BaseAdapter createAdapter() {
        listUsersAdapter = new ListUsersAdapter(listUsersActivity.retrieveContext());
        return listUsersAdapter;
    }

    @Override
    public boolean loadUsers() {
        boolean areUsersBeingLoaded = false;
        if (this.canRequestMoreUsers) {
            this.canRequestMoreUsers = false;
            areUsersBeingLoaded = true;
            this.subscribe = this.api.retrieveUsers(sinceGitHubUsers)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onUsersLoaded, this::onError);
        }
        return areUsersBeingLoaded;
    }

    private void onUsersLoaded(Response<List<User>> response) {
        if (response.code() == HttpURLConnection.HTTP_OK) {
            this.users.addAll(response.body());
            this.listUsersAdapter.setListData(this.users);
            this.listUsersAdapter.notifyDataSetChanged();
            this.canRequestMoreUsers = true;
            parseHeader(response.headers());
        }
        this.subscribe.unsubscribe();
    }

    private void parseHeader(Headers headers) {
        String link = headers.get(Constants.LINK_HEADER);
        if (!TextUtils.isEmpty(link)) {
            try {
                String[] calls = link.split(",");
                String[] futureCall = calls[0].split(";");
                if (Constants.LAST_ELEMENT.equals(futureCall[1])) {
                    this.canRequestMoreUsers = false;
                } else {
                    this.sinceGitHubUsers = Integer.parseInt(futureCall[0].substring(futureCall[0].lastIndexOf("=") + 1, futureCall[0].lastIndexOf(">")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
        this.subscribe.unsubscribe();
    }

    @Override
    public String retrieveUserProfilePage(int position) {
        return this.users.get(position).getHtmlUrl();
    }
}
