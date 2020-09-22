package com.epam.automation.model;

import java.util.Objects;

public class User {

    private String localSSD;
    private String desiredInstancesNumber;
    private String firstName;
    private String lastName;

    public User(String localSSD, String desiredInstancesNumber, String firstName, String lastName) {
        this.localSSD = localSSD;
        this.desiredInstancesNumber = desiredInstancesNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLocalSSD() {
        return localSSD;
    }

    public void setLocalSSD(String localSSD) {
        this.localSSD = localSSD;
    }

    public String getDesiredInstancesNumber() {
        return desiredInstancesNumber;
    }

    public void setDesiredInstancesNumber(String desiredInstancesNumber) {
        this.desiredInstancesNumber = desiredInstancesNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "region='" + localSSD + '\'' +
                ", desiredInstancesNumber='" + desiredInstancesNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(localSSD, user.localSSD) &&
                Objects.equals(desiredInstancesNumber, user.desiredInstancesNumber) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localSSD, desiredInstancesNumber, firstName, lastName);
    }
}
