package com.raj.task.userdetail;

import android.app.Activity;
import android.content.Intent;

import com.raj.task.data.User;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by dhiraj on 1/10/17.
 */

public class UserDetailsPresenter implements UserDetailsContract.Presenter {
    private User user;
    private UserDetailsContract.View view;

    public UserDetailsPresenter(User user, UserDetailsContract.View view) {
        this.user = user;
        this.view = view;
        view.setPresenter(this);
    }


    @Override
    public void start() {
        populateData(user);
    }

    public void populateData(User user) {
        view.populateData(user);
    }

    @Override
    public void edit() {
        view.showEdit(user);
    }

    @Override
    public void result(int requestCode, int resultCode, Intent intent) {
        if (Activity.RESULT_OK == resultCode) {
            User user = (User) intent.getSerializableExtra("KEY_USER");
            this.user = checkNotNull(user);
            populateData(user);
        }
    }
}
