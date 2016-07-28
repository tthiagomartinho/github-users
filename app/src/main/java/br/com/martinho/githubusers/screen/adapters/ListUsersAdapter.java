package br.com.martinho.githubusers.screen.adapters;

import android.content.Context;
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
public class ListUsersAdapter extends BaseAdapter {

    private Context context;
    private List<User> users;
    private LayoutInflater mInflater;

    public ListUsersAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public User getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_list_users, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        User user = getItem(position);

        holder.name.setText(user.getLogin());
        Picasso.with(context).load(user.getAvatarUrl()).resize(100, 100).into(holder.picture);

        return convertView;
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
