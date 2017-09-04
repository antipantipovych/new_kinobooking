package com.kinobooking.secure.dto;

import com.kinobooking.secure.validator.PasswordMatches;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Екатерина on 17.08.2017.
 */
@PasswordMatches
public class ClientDto {

    @NotEmpty(message="Password can not be empty")
    //@NotEmpty
    @Size(min=6, message = "Password must be more then 6 chars")

    private String password;
    @NotEmpty(message="First name can not be empty")
    //@NotEmpty

    private String firstName;
    @NotEmpty(message="Last name can not be empty")
    //@NotEmpty

    private String lastName;
    @Pattern(regexp = "^(?:[a-zA-Z0-9_'^&/+-])+(?:\\.(?:[a-zA-Z0-9_'^&/+-])+)" +
            "*@(?:(?:\\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\.)" +
            "{3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\]?)|(?:[a-zA-Z0-9-]+\\.)" +
            "+(?:[a-zA-Z]){2,}\\.?)$",
            message = "Not proper email")
    //@Email
    @NotEmpty(message="Email can not be empty")
   // @NotEmpty
    private String email;
    @NotEmpty(message="Password can not be empty")
    //@NotEmpty
    private String confirmPass;

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public ClientDto( String password, String firstName, String lastName, String email, String confirmPass) {

        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.confirmPass = confirmPass;
    }

    public ClientDto() {
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                 " password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", confirmPass='" + confirmPass + '\'' +
                '}';
    }
}

