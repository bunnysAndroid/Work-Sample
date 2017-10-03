package com.raj.task.adduser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.raj.task.R;
import com.raj.task.data.User;
import com.raj.task.data.local.DbController;
import com.raj.task.util.ActivityUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class AddEditUserActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_TASK = 100;
    public static final int REQUEST_EDIT_TASK = 101;
    AddEditUserPresenter addEditUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_user);
        User user = (User) getIntent().getSerializableExtra("KEY_USER");
        AddEditUserFragment addEditUserFragment =
                (AddEditUserFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (addEditUserFragment == null) {
            // Create the fragment
            addEditUserFragment = AddEditUserFragment.newInstance(user);
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), addEditUserFragment, R.id.contentFrame);
        }

        addEditUserPresenter = new AddEditUserPresenter(user,
                DbController.getInstance(getApplicationContext()), addEditUserFragment);

    }

}
