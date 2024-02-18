package java51.useraccount.service;

import java51.useraccount.dto.NewUserDto;
import java51.useraccount.dto.RoleDto;
import java51.useraccount.dto.UpdateUserDto;
import java51.useraccount.dto.UserDto;

public interface UserService {

    UserDto registerUser (NewUserDto newUserDto);

    UserDto deleteUser (String login);

    UserDto updateUser (String login, UpdateUserDto updateUserDto);

    UserDto getUser (String login);

    RoleDto addRole (String login, String role);

    RoleDto deleteRole (String login, String role);

    void changePassword (String login, String newPassword);

}
