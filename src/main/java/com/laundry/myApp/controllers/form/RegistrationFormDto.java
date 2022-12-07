package com.laundry.myApp.controllers.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter @Setter
public class RegistrationFormDto {

    @NotNull
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @Email(message = "invalid e-mail")
    private String email;

    @NotEmpty(message = "The username must be informed")
    @Size(min = 4, message = "Your username must be at least 4 characters long")
    private String username;

    @NotEmpty(message = "Your password must be informed")
    @Size(min = 3, message = "Your password must be at least 3 characters long")
    private String password;

}
