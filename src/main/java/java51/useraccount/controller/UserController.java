package java51.useraccount.controller;

import java51.useraccount.dto.NewUserDto;
import java51.useraccount.dto.RoleDto;
import java51.useraccount.dto.UpdateUserDto;
import java51.useraccount.dto.UserDto;
import java51.useraccount.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Base64;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class UserController {
    final UserService userService;

    @PostMapping("/register")
    public UserDto registerUser(@RequestBody NewUserDto newUserDto) {
        return userService.registerUser(newUserDto);
    }

//    @PostMapping("/login")
//    public UserDto login(@RequestHeader("Authorization")  String token){
//        token = token.split(" ")[1];
//        String credentionals = new String(Base64.getDecoder().decode(token));
//        return userService.getUser(credentionals.split(":")[0]);
//    }

    @PostMapping
    public UserDto login(Principal principal) {
        return userService.getUser(principal.getName());
    }

    @DeleteMapping("/user/{login}")
    public UserDto deleteUser(@PathVariable("login") String login) {
        return userService.deleteUser(login);
    }

    @PutMapping("/user/{user}")
    public UserDto updateUser(@PathVariable("user") String login, @RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(login, updateUserDto);
    }

    @GetMapping ("/user/{user}")
    public UserDto getUser(@PathVariable("user") String login) {
        return userService.getUser(login);
    }

    @PutMapping ("/user/{user}/role/{role}")
    public RoleDto addRole(@PathVariable("user") String login, @PathVariable String role) {
        return userService.addRole(login, role);
    }

    @DeleteMapping("/user/{user}/role/{role}")
    public RoleDto deleteRole(@PathVariable("user") String login, @PathVariable String role) {
        return userService.deleteRole(login, role);
    }

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(Principal principal, @RequestHeader("X-Password") String newPassword){
        userService.changePassword(principal.getName(), newPassword);
    }
}
