package com.github.andygo298.testTask.controller.request;

import com.github.andygo298.testTask.model.enums.Role;
import com.github.andygo298.testTask.model.enums.Status;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@Validated
public class EditUserRq {

    private Integer id;
    @NotEmpty(message = "Please fill the username.")
    @Pattern(regexp = "^[a-zA-Z]{3,16}$", message = "Username must be in interval 3-16 characters.\n Latin letters only.")
    private String username;
    @NotEmpty(message = "Please fill the first name.")
    @Pattern(regexp = "^[a-zA-Z]{1,16}$", message = "First name must be in interval 1-16 characters.\n Latin letters only.")
    private String firstName;
    @NotEmpty(message = "Please fill the last name.")
    @Pattern(regexp = "^[a-zA-Z]{1,16}$", message = "Last name must be in interval 1-16 characters.\n Latin letters only.")
    private String lastName;
    private Status isActive;
    private Set<Role> roles;
}
