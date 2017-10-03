package com.raj.task.users;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.raj.task.adduser.AddEditUserActivity;
import com.raj.task.data.DataSource;
import com.raj.task.data.User;
import com.raj.task.data.local.DbController;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by dhiraj on 27/9/17.
 */

public class UserPresenter implements UserContract.Presenter {
    private final UserContract.View mUserView;
    private DbController dbController;
    public List<User> users;

    public UserPresenter(@NonNull DbController dbController, UserContract.View mUserView) {
        this.dbController = checkNotNull(dbController, "dbController cannot be null");
        this.mUserView = checkNotNull(mUserView, "UserView can not be null");
        mUserView.setPresenter(this);

    }

    @Override
    public void start() {
        loadUsers();
    }


    @Override
    public void loadUsers() {
        dbController.getUsers(new DataSource.LoadUsersCallback() {

            @Override
            public void onUserLoaded(List<User> users) {
                UserPresenter.this.users = users;
                mUserView.showUsersList(users);
            }

            @Override
            public void onDataNotAvailable() {
                Log.d("DB", "saveUser: " + 0);
            }
        });
    }

    @Override
    public void addNewUser() {
        mUserView.showAddUser();
    }

    @Override
    public void openUserDetails(User user) {
        mUserView.showUserDetailsUi(user);
    }

    @Override
    public void result(int requestCode, int resultCode) {
        if (AddEditUserActivity.REQUEST_ADD_TASK == requestCode && Activity.RESULT_OK == resultCode) {
            mUserView.showSuccessfullyAdded();
        } else if (AddEditUserActivity.REQUEST_EDIT_TASK == requestCode && Activity.RESULT_OK == resultCode) {
            mUserView.showSuccessfullyUpdated();
        }
    }

    @Override
    public void attachSwipeCallBack() {
        mUserView.attacheItemTouchToRecyclerView(itemTouchHelper);
    }

    @Override
    public void deleteUser(long id) {
        dbController.deleteUser(id);
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

            return true;
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);

        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            if (direction == ItemTouchHelper.LEFT) {

                mUserView.swipeToDelete(viewHolder.getAdapterPosition());
            }
        }
    };
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);


}
