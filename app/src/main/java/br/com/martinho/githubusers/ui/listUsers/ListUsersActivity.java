package br.com.martinho.githubusers.ui.listUsers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import br.com.martinho.githubusers.R;
import br.com.martinho.githubusers.model.User;
import br.com.martinho.githubusers.ui.base.BaseActivity;
import br.com.martinho.githubusers.util.listeners.EndlessScrollListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ListUsersActivity extends BaseActivity implements ListUsersContract.View {

    @Inject
    ListUsersContract.Presenter presenter;

    @BindView(R.id.activity_list_users_data)
    ListView usersList;

    @BindView(R.id.activity_list_users_progress_bar)
    ProgressBar progressBar;

    private ListUsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        ButterKnife.bind(this);

        getUiComponent().inject(this);
        this.presenter.setView(this);

        setUsersListUp();
        this.presenter.loadUsers(1);
    }

    private void setUsersListUp() {
        this.usersList.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                //TODO check if there are more pages to be loaded
                presenter.loadUsers(page);
                return true;
            }
        });
    }

    @OnItemClick(R.id.activity_list_users_data)
    public void onUserClicked(int position) {
        String url = this.adapter.getItem(position).getHtmlUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void showLoadingLayout() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void addData(List<User> users) {
        if (this.adapter == null) {
            this.adapter = new ListUsersAdapter(users);
            this.usersList.setAdapter(this.adapter);
        } else {
            this.adapter.addMoreData(users);
            this.adapter.notifyDataSetChanged();
        }
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorLayout(int page) {
        if (isConnectedToInternet()) {
            Toast.makeText(this, R.string.error_getting_users, Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name)
                    .setMessage(R.string.dialog_no_internet)
                    .setPositiveButton(R.string.try_again, (dialog, id) -> ListUsersActivity.this.presenter.loadUsers(page));
            builder.create().show();
        }
    }

    @Override
    protected void onDestroy() {
        this.presenter.onDestroy();
        super.onDestroy();
    }
}
