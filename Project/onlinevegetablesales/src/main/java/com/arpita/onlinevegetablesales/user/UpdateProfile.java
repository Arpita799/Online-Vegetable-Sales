package com.arpita.onlinevegetablesales.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProfile {
    @NotNull(message="is required")
    @Size(min=1,message = "is required")
    private String firstName;
    @NotNull(message="is required")
    @Size(min=1,message = "is required")
    private String lastName;
    @NotNull(message="is required")
    @Size(min=1,message = "is required")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;

    public UpdateProfile(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    public UpdateProfile(){

    }
}
