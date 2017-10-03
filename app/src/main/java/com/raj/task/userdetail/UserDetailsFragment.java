package com.raj.task.userdetail;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.raj.task.R;
import com.raj.task.adduser.AddEditUserActivity;
import com.raj.task.data.User;
import com.raj.task.util.DateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserDetailsFragment extends Fragment implements UserDetailsContract.View {
    private static final String KEY_USER = "KEY_USER";
    @BindView(R.id.f_name)
    TextView fName;
    @BindView(R.id.l_name)
    TextView lName;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.dob)
    TextView dob;
    @BindView(R.id.btn_save_update)
    Button btnSaveUpdate;
    Unbinder unbinder;
    private User user;
    private UserDetailsContract.Presenter presenter;

    public static UserDetailsFragment newInstance(User user) {
        UserDetailsFragment fragment = new UserDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable(KEY_USER);
            checkNotNull(user, "User Data cannot be null");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter.start();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_save_update)
    public void onViewClicked() {
        presenter.edit();
    }

    @Override
    public void setPresenter(UserDetailsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showUserList() {
        getActivity().onBackPressed();
    }

    @Override
    public void showEdit(User user) {
        Intent intent = new Intent(getContext(), AddEditUserActivity.class);
        intent.putExtra("KEY_USER", user);
        startActivityForResult(intent, AddEditUserActivity.REQUEST_ADD_TASK);
    }

    @Override
    public void populateData(User user) {
        fName.setText(user.getfName());
        lName.setText(user.getlName());
        email.setText(user.getEmail());
        phoneNumber.setText(user.getPhoneNumber());
        dob.setText(DateUtil.getDisplayDate(user.getDob()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.result(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
