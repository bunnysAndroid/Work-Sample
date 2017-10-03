package com.raj.task.data;

import java.io.Serializable;

/**
 * Created by dhiraj on 27/9/17.
 */

public class User implements Serializable {
    private long id;
    private String lName;
    private String fName;
    private String email;
    private String phoneNumber;
    private long dob;

    public User() {}

    public User(long id, String lName, String fName, String email, String phoneNumber, long dob) {
        this.id = id;
        this.lName = lName;
        this.fName = fName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
    }


    public long getDob() {
        return dob;
    }

    public void setDob(long dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
