package com.raj.task.userdetail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.raj.task.R;
import com.raj.task.adduser.AddEditUserFragment;
import com.raj.task.adduser.AddEditUserPresenter;
import com.raj.task.data.User;
import com.raj.task.data.local.DbController;
import com.raj.task.util.ActivityUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class UserDetailsActivity extends AppCompatActivity {

    private UserDetailsPresenter userDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        User user = (User) checkNotNull(getIntent().getSerializableExtra("KEY_USER"));
        UserDetailsFragment userDetailsFragment =
                (UserDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (userDetailsFragment == null) {
            // Create the fragment
            userDetailsFragment = UserDetailsFragment.newInstance(user);
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), userDetailsFragment, R.id.contentFrame);
        }
        userDetailPresenter = new UserDetailsPresenter(user, userDetailsFragment);

    }

}
