package java51.useraccount.controller;

import java51.useraccount.dto.NewUserDto;
import java51.useraccount.dto.RoleDto;
import java51.useraccount.dto.UpdateUserDto;
import java51.useraccount.dto.UserDto;
import java51.useraccount.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    final UserService userService;

    @PostMapping("{baseUrl}/register")
    public UserDto registerUser(@PathVariable String baseUrl, @RequestBody NewUserDto newUserDto) {
        return userService.registerUser(baseUrl, newUserDto);
    }

    @DeleteMapping("{baseUrl}/user/{user}")
    public UserDto deleteUser(@PathVariable String baseUrl, @PathVariable("user") String login) {
        return userService.deleteUser(baseUrl, login);
    }

    @PutMapping("{baseUrl}/user/{user}")
    public UserDto updateUser(@PathVariable String baseUrl, @PathVariable("user") String login, @RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(baseUrl, login, updateUserDto);
    }

    @GetMapping ("{baseUrl}/user/{user}")
    public UserDto getUser(@PathVariable String baseUrl, @PathVariable("user") String login) {
        return userService.getUser(baseUrl, login);
    }

    @PutMapping ("{baseUrl}/user/{user}/role/{role}")
    public RoleDto addRole(@PathVariable String baseUrl, @PathVariable("user") String login, @PathVariable String role) {
        return userService.addRole(baseUrl, login, role);
    }

    @DeleteMapping("{baseUrl}/user/{user}/role/{role}")
    public RoleDto deleteRole(@PathVariable String baseUrl, @PathVariable("user") String login, @PathVariable String role) {
        return userService.deleteRole(baseUrl, login, role);
    }
}
