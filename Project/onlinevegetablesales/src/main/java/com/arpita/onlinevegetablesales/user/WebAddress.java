package com.arpita.onlinevegetablesales.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WebAddress {
    @NotNull(message="is required")
    @Size(min=1,message = "is required")
    private String fullName;
    @NotNull(message="is required")
    @Size(min=1,message = "is required")
    @Pattern(regexp = "^(\\+91[\\-\\s]?)?[0]?(91)?[6789]\\d{9}$")
    private String phoneNumber;
    @NotNull(message="is required")
    @Size(min=1,message = "is required")
    @Pattern(regexp = "^[1-9][0-9]{5}$")
    private String pinCode;
    @NotNull(message="is required")
    @Size(min=1,message = "is required")
    private String address;
    @NotNull(message="is required")
    @Size(min=1,message = "is required")
    private String country;
    @NotNull(message="is required")
    @Size(min=1,message = "is required")
    private String state;
    @NotNull(message="is required")
    @Size(min=1,message = "is required")
    private String city;
}
