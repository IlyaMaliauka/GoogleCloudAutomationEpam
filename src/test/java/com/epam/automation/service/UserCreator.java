package com.epam.automation.service;

import com.epam.automation.model.User;

public class UserCreator {

    public static final String USER_FIRST_NAME = "Ilya";
    public static final String USER_LAST_NAME = "Maliauka";
    public static final String USER_DESIRED_INSTANCES_NUMBER = "4";
    public static final String USER_REGION = "Europe";

    public static User withCredentialsFromProperty() {
        return new User(USER_REGION, USER_DESIRED_INSTANCES_NUMBER, USER_FIRST_NAME, USER_LAST_NAME);
    }
}
