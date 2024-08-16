package com.taskmanagement.ass.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}