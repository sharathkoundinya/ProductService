package org.redlotus.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.redlotus.productservice.dtos.Role;

import java.util.List;

@Getter
@Setter

public class UserDto {
    private String email;
    private String name;
    private List<Role> roles;
    private Boolean isEmailVerified;

}
