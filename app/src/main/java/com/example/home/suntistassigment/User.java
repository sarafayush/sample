package com.example.home.suntistassigment;

/**
 * Created by home on 21/03/18.
 */

public class User {
    private String name;
    private String cellNumber;
    private String gender;

    public User(String name, String cellNumber, String gender) {
        this.name = name;
        this.cellNumber = cellNumber;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
