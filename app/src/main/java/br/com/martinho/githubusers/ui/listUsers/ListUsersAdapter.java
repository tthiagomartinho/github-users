package br.com.martinho.githubusers.ui.listUsers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.martinho.githubusers.R;
import br.com.martinho.githubusers.model.User;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Thiago on 27/07/2016.
 */
class ListUsersAdapter extends BaseAdapter {

    private List<User> users;

    ListUsersAdapter(List<User> users) {
        this.users = users;
    }

    void addMoreData(List<User> users) {
        this.users.addAll(users);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_users, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        User user = getItem(position);

        holder.name.setText(user.getLogin());
        Picasso.with(view.getContext()).load(user.getAvatarUrl()).resize(40, 40).into(holder.picture);

        return view;
    }

    @Override
    public int getCount() {
        return users == null ? 0 : users.size();
    }

    @Override
    public User getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder {
        @BindView(R.id.adapter_list_users_picture)
        ImageView picture;
        @BindView(R.id.adapter_list_users_name)
        TextView name;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
