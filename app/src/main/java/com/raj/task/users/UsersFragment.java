package com.raj.task.users;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raj.task.R;
import com.raj.task.adduser.AddEditUserActivity;
import com.raj.task.data.User;
import com.raj.task.userdetail.UserDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment implements UserContract.View {


    @BindView(R.id.users_list)
    RecyclerView usersListView;
    @BindView(R.id.no_user_view)
    TextView noUserView;
    @BindView(R.id.fab_add_user)
    FloatingActionButton fab;
    Unbinder unbinder;
    UserAdapter userAdapter;
    UserContract.Presenter presenter;

    public UsersFragment() {
    }

    public static UsersFragment newInstance() {
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        usersListView.setLayoutManager(linearLayoutManager);
        userAdapter = new UserAdapter(new ArrayList<User>(0));
        usersListView.setAdapter(userAdapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addNewUser();
            }
        });
        presenter.attachSwipeCallBack();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.result(requestCode, resultCode);
    }

    @Override
    public void setPresenter(UserContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showUsersList(List<User> users) {
        noUserView.setVisibility(View.GONE);
        usersListView.setVisibility(View.VISIBLE);
        userAdapter.setUserList(users);
    }

    @Override
    public void showAddUser() {
        Intent intent = new Intent(getContext(), AddEditUserActivity.class);
        startActivityForResult(intent, AddEditUserActivity.REQUEST_ADD_TASK);

    }

    @Override
    public void showUserDetailsUi(User user) {
        Intent intent = new Intent(getActivity(), UserDetailsActivity.class);
        intent.putExtra("KEY_USER", user);
        startActivity(intent);
    }


    @Override
    public void showNoUser() {
        noUserView.setVisibility(View.VISIBLE);
        usersListView.setVisibility(View.GONE);
    }

    @Override
    public void showSuccessfullyAdded() {
        showMessage(getString(R.string.successfully_edited_user_message));
    }

    @Override
    public void showSuccessfullyUpdated() {
        showMessage(getString(R.string.successfully_saved_user_message));
    }

    @Override
    public void attacheItemTouchToRecyclerView(ItemTouchHelper itemTouchHelper) {
        itemTouchHelper.attachToRecyclerView(usersListView);
    }

    @Override
    public void swipeToDelete(int adapterPosition) {
        userAdapter.removeUser(adapterPosition);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
        private List<User> users;

        UserAdapter(List<User> users) {this.users = users;}

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.user_item, parent, false);

            return new ViewHolder(itemView);
        }

        private void setUserList(List<User> users) {
            this.users = checkNotNull(users);
            notifyDataSetChanged();
        }

        private void addUser(User user) {
            this.users.add(user);
            notifyItemInserted(users.size() - 1);
        }

        private void removeUser(int position) {
            presenter.deleteUser(users.get(position).getId());
            users.remove(position);
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final User user = users.get(position);
            holder.name.setText(user.getfName() + " " + user.getlName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.openUserDetails(user);
                }
            });

        }

        @Override
        public int getItemCount() {
            return users.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.name)
            TextView name;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }


    }
}
