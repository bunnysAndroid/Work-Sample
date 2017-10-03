package com.raj.task.users;

import android.support.v7.widget.helper.ItemTouchHelper;

import com.raj.task.BasePresenter;
import com.raj.task.BaseView;
import com.raj.task.data.User;

import java.util.List;

/**
 * Created by dhiraj on 27/9/17.
 */

public class UserContract {
    interface View extends BaseView<Presenter> {
        void showUsersList(List<User> users);

        void showAddUser();

        void showUserDetailsUi(User user);

        void showNoUser();

        void showSuccessfullyAdded();

        void showSuccessfullyUpdated();

        void attacheItemTouchToRecyclerView(ItemTouchHelper itemTouchHelper);

        void swipeToDelete(int adapterPosition);
    }

    interface Presenter extends BasePresenter {
        void loadUsers();

        void addNewUser();

        void openUserDetails(User user);

        void result(int requestCode, int resultCode);

        void attachSwipeCallBack();

        void deleteUser(long id);
    }
}
