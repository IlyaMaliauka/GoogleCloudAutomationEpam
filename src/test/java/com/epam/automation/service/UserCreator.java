package com.epam.automation.service;

import com.epam.automation.model.User;

public class UserCreator {

    public static final String TESTDATA_USER_FIRST_NAME = "testdata.user.firstName";
    public static final String TESTDATA_USER_LAST_NAME = "testdata.user.lastName";
    public static final String TESTDATA_USER_DESIRED_INSTANCES_NUMBER = "testdata.user.instancesNumber";
    public static final String TESTDATA_USER_LOCAL_SSD = "testdata.user.localSSD";

    public static User withCredentialsFromProperty() {
        return new User(TestDataReader.getTestData(TESTDATA_USER_LOCAL_SSD),
                TestDataReader.getTestData(TESTDATA_USER_DESIRED_INSTANCES_NUMBER),
                TestDataReader.getTestData(TESTDATA_USER_FIRST_NAME),
                TestDataReader.getTestData(TESTDATA_USER_LAST_NAME));
    }
}
