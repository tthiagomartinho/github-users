package br.com.martinho.githubusers.screen.activities.listUsers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

import javax.inject.Inject;

import br.com.martinho.githubusers.R;
import br.com.martinho.githubusers.screen.base.BaseActivity;
import br.com.martinho.githubusers.util.listeners.EndlessScrollListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ListUsersActivity extends BaseActivity implements IListUsersActivity {

    @Inject
    IListUsersActivityPresenter listUsersActivityPresenter;

    @BindView(R.id.activity_list_users_data)
    ListView usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        ButterKnife.bind(this);

        ListUsersActivityComponent listUsersActivityComponent = DaggerListUsersActivityComponent
                .builder()
                .applicationComponent((retrieveApplication()).getApplicationComponent())
                .listUsersActivityModule(new ListUsersActivityModule(this))
                .build();

        listUsersActivityComponent.inject(this);

        setUsersListUp();

        listUsersActivityPresenter.loadUsers();
    }

    private void setUsersListUp() {
        BaseAdapter baseAdapter = listUsersActivityPresenter.createAdapter();
        this.usersList.setAdapter(baseAdapter);

        this.usersList.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                return listUsersActivityPresenter.loadUsers();
            }
        });
    }

    @OnItemClick(R.id.activity_list_users_data)
    public void onUserClicked(int position) {
        String url = listUsersActivityPresenter.retrieveUserProfilePage(position);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public ListView getUsersList() {
        return usersList;
    }
}
