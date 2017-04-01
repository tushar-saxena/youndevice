package com.youndevice.rest.dto;


import org.hibernate.validator.constraints.NotBlank;

public class UserDTO {

    @NotBlank(message = "firstName.is.mandatory")
    private String firstName;

    @NotBlank(message = "lastName.is.mandatory")
    private String lastName;

    @NotBlank(message = "password.is.mandatory")
    private String password;

    @NotBlank(message = "emailId.is.mandatory")
    private String emailId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
