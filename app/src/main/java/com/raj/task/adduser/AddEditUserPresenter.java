package com.raj.task.adduser;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.util.Log;
import android.widget.DatePicker;

import com.raj.task.adduser.AddEditUserContract.Presenter;
import com.raj.task.data.User;
import com.raj.task.data.local.DbController;
import com.raj.task.imagepicker.ImagePickerDialog;
import com.raj.task.imagepicker.PickerBuilder;
import com.raj.task.util.DateUtil;

import java.util.Calendar;

/**
 * Created by dhiraj on 28/9/17.
 */

public class AddEditUserPresenter implements Presenter {
    private User user;
    private final AddEditUserContract.View addEditUserView;
    private DbController dbController;

    public AddEditUserPresenter(User user, DbController dbController, AddEditUserContract.View addEditUserView) {
        this.user = user;
        this.addEditUserView = addEditUserView;
        this.dbController = dbController;
        addEditUserView.setPresenter(this);
    }


    @Override
    public void start() {
        if (!isNewUser()) {
            populateData();
            addEditUserView.setButtonTitle("Update");
        }else {
            addEditUserView.setButtonTitle("Save");
        }
    }

    @Override
    public void saveUpdateData(String fname, String lname, String email, String phoneNumber, String dob) {

        if (isNewUser()) {
            user = new User();
            user.setfName(fname);
            user.setlName(lname);
            user.setPhoneNumber(phoneNumber);
            user.setEmail(email);
            user.setDob(DateUtil.getDateInMillis(dob));
            dbController.saveUser(user);
            addEditUserView.showUserList();
        } else {
            user.setfName(fname);
            user.setlName(lname);
            user.setPhoneNumber(phoneNumber);
            user.setEmail(email);
            user.setDob(DateUtil.getDateInMillis(dob));
            dbController.updateUser(user);
            addEditUserView.showUserDetail(user);
        }

    }

    @Override
    public void populateData() {
        addEditUserView.populateUserData(user);
        addEditUserView.showDate(DateUtil.getDisplayDate(user.getDob()));
    }

    @Override
    public void selectDOB(String dob) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(DateUtil.getDateInMillis(dob));
        addEditUserView.showDatePicker(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(Calendar.YEAR, year);
                newDate.set(Calendar.MONTH, month);
                newDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                addEditUserView.showDate(DateUtil.getDisplayDate(newDate.getTimeInMillis()));
            }
        }, calendar);
    }

    PickerBuilder.onImageReceivedListener imageReceivedListener = new PickerBuilder.onImageReceivedListener() {
        @Override
        public void onImageReceived(Uri imageUri) {
            Log.d("IMAGE", "onImageReceived: got image");
        }
    };
    PickerBuilder.onPermissionRefusedListener permissionRefusedListener =
            new PickerBuilder.onPermissionRefusedListener() {
                @Override
                public void onPermissionRefused() {

                }
            };

    @Override
    public void selectPicture() {
        addEditUserView.showPicturePicker(new ImagePickerDialog.ImagePickerListener() {
            @Override
            public void pickFromGallery() {
                addEditUserView.showGalleryPicker(imageReceivedListener, permissionRefusedListener);
            }

            @Override
            public void pickFromCamera() {
                addEditUserView.showCameraPicker(imageReceivedListener, permissionRefusedListener);

            }
        });
    }

    private boolean isNewUser() {
        return user == null;
    }
}
