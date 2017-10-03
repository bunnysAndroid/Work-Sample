package com.raj.task.users;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.raj.task.R;
import com.raj.task.data.local.DbController;
import com.raj.task.util.ActivityUtils;

public class UsersActivity extends AppCompatActivity {
    UserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UsersFragment usersFragment =
                (UsersFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (usersFragment == null) {
            // Create the fragment
            usersFragment = UsersFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), usersFragment, R.id.contentFrame);
        }
        userPresenter = new UserPresenter(
                DbController.getInstance(getApplicationContext()), usersFragment);

    }
}
