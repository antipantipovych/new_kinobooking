package com.kinobooking.secure.dto;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Екатерина on 06.09.2017.
 */
public class ChangeClientDto {
    @NotEmpty(message="First name can not be empty")
    private String firstName;
    @NotEmpty(message="Last name can not be empty")
    private String lastName;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
