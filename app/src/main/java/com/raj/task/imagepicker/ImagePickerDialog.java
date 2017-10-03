package com.raj.task.imagepicker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.raj.task.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dhiraj on 17/1/17.
 */

public class ImagePickerDialog extends DialogFragment {

    public void setListener(ImagePickerListener listener) {
        this.listener = listener;

    }

    ImagePickerListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_image_picker_dialog, null);

        ButterKnife.bind(this, view);
        final Dialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Window window = alertDialog.getWindow();
        //window.setBackgroundDrawableResource(R.drawable.bg_transperent_round_corner);

        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        return alertDialog;
    }


    @Override
    public void onStart() {
        super.onStart();
    }


    @OnClick({R.id.startGalleryBtn, R.id.startCameraBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startGalleryBtn:
                listener.pickFromGallery();
                dismiss();
                break;
            case R.id.startCameraBtn:
                listener.pickFromCamera();
                dismiss();
                break;
        }
    }

    public interface ImagePickerListener {
        void pickFromGallery();

        void pickFromCamera();
    }
}
