package com.raj.task.imagepicker;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

/**
 * Created by raj on 10/10/2016.
 */

public class CameraPickerManager extends PickerManager {

    public CameraPickerManager(Activity activity) {
        super(activity);
    }

    protected void sendToExternalApp() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mProcessingPhotoUri = getImageFile();
/*
        Uri photoURI = FileProvider.getUriForFile(activity.getApplicationContext(), activity.getApplicationContext().getPackageName() + ".provider", new File(mProcessingPhotoUri.getPath()));
        activity.grantUriPermission("com.android.camera", photoURI,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
*/

        intent.putExtra(MediaStore.EXTRA_OUTPUT, mProcessingPhotoUri);
        activity.startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
    }
}
