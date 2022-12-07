package com.laundry.myApp.controllers.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class UserUpdateFormDto {

    @NotEmpty(message = "Destination email must not be empty")
    private String destinationEmail;

    private String name;

    private String username;

}
