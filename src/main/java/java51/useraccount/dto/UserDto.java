package java51.useraccount.dto;

import java51.useraccount.model.Role;

import java.util.Set;

public class UserDto {
    String login;
    String firstName;
    String lastName;
    Set<Role> roles;
}
