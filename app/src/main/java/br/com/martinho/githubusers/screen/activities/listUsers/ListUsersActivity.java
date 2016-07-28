package br.com.martinho.githubusers.screen.activities.listUsers;

import android.os.Bundle;
import android.widget.ListView;

import javax.inject.Inject;

import br.com.martinho.githubusers.R;
import br.com.martinho.githubusers.screen.adapters.ListUsersAdapter;
import br.com.martinho.githubusers.screen.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ListUsersActivity extends BaseActivity implements IListUsersActivity {

    @Inject
    IListUsersActivityPresenter listUsersActivityPresenter;

    @BindView(R.id.activity_list_users_data)
    ListView data;

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

        listUsersActivityPresenter.loadUsers();
    }

    @OnItemClick(R.id.activity_list_users_data)
    public void onUserClicked(int position) {
    }

    @Override
    public void onUsersLoaded(ListUsersAdapter listUsersAdapter) {
        data.setAdapter(listUsersAdapter);
    }
}
