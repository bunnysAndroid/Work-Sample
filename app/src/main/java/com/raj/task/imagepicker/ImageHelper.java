package com.raj.task.imagepicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * Created by raj on 28/2/17.
 */

public class ImageHelper {
    public static String getBase64Image(Context context, Uri uri) {
        String result = null;
        if (uri == null) {
            return null;
        }
        final InputStream imageStream;
        try {
            imageStream = context.getContentResolver().openInputStream(uri);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            result = encodeImage(selectedImage);
            Log.d("ImageHelper", "getBase64Image: " + result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String encodeImage(Bitmap bm) {
        if (bm == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.NO_WRAP);
        encImage = "data:image/jpeg;base64," + encImage;
        return encImage;
    }


}
