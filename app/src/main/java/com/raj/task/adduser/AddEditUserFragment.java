package com.raj.task.adduser;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.raj.task.R;
import com.raj.task.data.User;
import com.raj.task.imagepicker.ImagePickerDialog;
import com.raj.task.imagepicker.PickerBuilder;
import com.raj.task.util.DateUtil;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddEditUserFragment extends Fragment implements AddEditUserContract.View {
    @BindView(R.id.user_profile)
    ImageView userProfile;
    @BindView(R.id.f_name)
    EditText fName;
    @BindView(R.id.l_name)
    EditText lName;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.dob)
    EditText dob;
    @BindView(R.id.btn_save_update)
    Button btnSaveUpdate;
    Unbinder unbinder;

    AddEditUserContract.Presenter presenter;
    private User user;

    public static AddEditUserFragment newInstance(User user) {

        AddEditUserFragment fragment = new AddEditUserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable("KEY_USER");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_edit_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        dob.setFocusable(false);
        presenter.start();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.user_profile, R.id.dob, R.id.btn_save_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_profile:
                presenter.selectPicture();
                break;
            case R.id.dob:
                presenter.selectDOB(dob.getText().toString());
                break;
            case R.id.btn_save_update:
                presenter.saveUpdateData(fName.getText().toString(),
                        lName.getText().toString()
                        , phoneNumber.getText().toString(),
                        email.getText().toString(),
                        dob.getText().toString()
                );
                break;
        }
    }

    @Override
    public void setPresenter(AddEditUserContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void populateUserData(User user) {
        fName.setText(user.getfName());
        lName.setText(user.getlName());
        phoneNumber.setText(user.getPhoneNumber());
        email.setText(user.getEmail());

    }

    @Override
    public void setButtonTitle(String title) {
        btnSaveUpdate.setText(title);
    }

    @Override
    public void showUserList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void showUserDetail(User user) {
        Intent intent = new Intent();
        intent.putExtra("KEY_USER", user);
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }


    @Override
    public void showDate(String date) {
        dob.setText(date);
    }

    @Override
    public void setProfilePic(Bitmap profilePic) {
        userProfile.setImageBitmap(profilePic);
    }

    @Override
    public void showDatePicker(DatePickerDialog.OnDateSetListener listener, Calendar date) {
        DatePickerDialog dialog = new DatePickerDialog(getContext(),
                listener,
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }

    @Override
    public void showPicturePicker(ImagePickerDialog.ImagePickerListener listener) {
        ImagePickerDialog dialog = new ImagePickerDialog();
        dialog.setListener(listener);
        dialog.show(getFragmentManager(), "PICK_PIC");
    }

    @Override
    public void showGalleryPicker(PickerBuilder.onImageReceivedListener listener, PickerBuilder.onPermissionRefusedListener refusedListener) {
        new PickerBuilder(getActivity(), PickerBuilder.SELECT_FROM_GALLERY)
                .setOnImageReceivedListener(listener)
                /*.setImageName("test")
                .setImageFolderName("testFolder")*/
                .setCropScreenColor(Color.CYAN)
                .setOnPermissionRefusedListener(refusedListener)
                .start();
    }

    @Override
    public void showCameraPicker(PickerBuilder.onImageReceivedListener listener, PickerBuilder.onPermissionRefusedListener refusedListener) {
        new PickerBuilder(getActivity(), PickerBuilder.SELECT_FROM_CAMERA)
                .setOnImageReceivedListener(listener)
               /* .setImageName("test")
                .setImageFolderName("testFolder")*/
                .setCropScreenColor(Color.CYAN)
                .setOnPermissionRefusedListener(refusedListener)
                .start();
    }


}
