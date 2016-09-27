package br.com.martinho.githubusers.ui.listUsers;

import java.util.List;

import br.com.martinho.githubusers.model.User;

/**
 * Created by Thiago on 27/09/2016.
 */

public interface ListUsersContract {

    interface View {

        void showLoadingLayout();

        void addData(List<User> users);

        void showErrorLayout(int page);
    }

    interface Presenter {

        void loadUsers(int page);

        void setView(View view);

        boolean canRequestMore(int page);

        void onDestroy();
    }
}
