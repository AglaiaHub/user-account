package java51.useraccount.service;

import java51.useraccount.dto.NewUserDto;
import java51.useraccount.dto.RoleDto;
import java51.useraccount.dto.UpdateUserDto;
import java51.useraccount.dto.UserDto;

public interface UserService {

    UserDto registerUser (String baseUrl, NewUserDto newUserDto);

    UserDto deleteUser (String baseUrl, String login);

    UserDto updateUser (String baseUrl, String login, UpdateUserDto updateUserDto);

    UserDto getUser (String baseUrl, String login);

    RoleDto addRole (String baseUrl, String login, String role);

    RoleDto deleteRole (String baseUrl, String login, String role);

}
