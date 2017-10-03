package com.raj.task.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by dhiraj on 27/9/17.
 */

public interface DataSource {
    interface LoadUsersCallback {

        void onUserLoaded(List<User> users);

        void onDataNotAvailable();
    }

    void getUsers(@NonNull LoadUsersCallback callback);

    void saveUser(@NonNull User user);

    void updateUser(@NonNull User user);

    void deleteUser(@NonNull long id);

}
