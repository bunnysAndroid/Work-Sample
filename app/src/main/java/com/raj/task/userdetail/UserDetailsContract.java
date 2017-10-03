package com.raj.task.userdetail;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;

import com.raj.task.BasePresenter;
import com.raj.task.BaseView;
import com.raj.task.adduser.AddEditUserContract;
import com.raj.task.data.User;
import com.raj.task.imagepicker.ImagePickerDialog;
import com.raj.task.imagepicker.PickerBuilder;

import java.util.Calendar;

/**
 * Created by dhiraj on 1/10/17.
 */

public class UserDetailsContract {
    interface View extends BaseView<UserDetailsContract.Presenter> {

        void showUserList();

        void showEdit(User user);

        void populateData(User user);

    }

    interface Presenter extends BasePresenter {

        void edit();

        void result(int requestCode, int resultCode, Intent intent);
    }
}
