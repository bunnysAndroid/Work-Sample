package com.raj.task.adduser;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;

import com.raj.task.BasePresenter;
import com.raj.task.BaseView;
import com.raj.task.data.User;
import com.raj.task.imagepicker.ImagePickerDialog;
import com.raj.task.imagepicker.PickerBuilder;
import com.raj.task.users.UserContract;

import java.util.Calendar;
import java.util.List;

/**
 * Created by dhiraj on 28/9/17.
 */

public class AddEditUserContract {
    interface View extends BaseView<AddEditUserContract.Presenter> {

        void populateUserData(User user);

        void setButtonTitle(String title);

        void showUserList();

        void showUserDetail(User user);

        void showDate(String date);

        void setProfilePic(Bitmap profilePic);

        void showDatePicker(DatePickerDialog.OnDateSetListener listener, Calendar date);

        void showPicturePicker(ImagePickerDialog.ImagePickerListener listener);

        void showGalleryPicker(PickerBuilder.onImageReceivedListener listener, PickerBuilder.onPermissionRefusedListener refusedListener);

        void showCameraPicker(PickerBuilder.onImageReceivedListener listener, PickerBuilder.onPermissionRefusedListener refusedListener);
    }

    interface Presenter extends BasePresenter {

        void saveUpdateData(String fname, String lname, String email, String phoneNumber, String dob);

        void populateData();

        void selectDOB(String dob);

        void selectPicture();

    }
}
