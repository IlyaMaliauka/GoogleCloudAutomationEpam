package com.epam.automation.model;

import java.util.Objects;

public class User {

    private String region;
    private String desiredInstancesNumber;
    private String firstName;
    private String lastName;

    public User(String region, String desiredInstancesNumber, String firstName, String lastName) {
        this.region = region;
        this.desiredInstancesNumber = desiredInstancesNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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
                "region='" + region + '\'' +
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
        return Objects.equals(region, user.region) &&
                Objects.equals(desiredInstancesNumber, user.desiredInstancesNumber) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(region, desiredInstancesNumber, firstName, lastName);
    }
}
