package java51.useraccount.service;

import java51.useraccount.dao.UserRepository;
import java51.useraccount.dto.NewUserDto;
import java51.useraccount.dto.RoleDto;
import java51.useraccount.dto.UpdateUserDto;
import java51.useraccount.dto.UserDto;
import java51.useraccount.dto.exception.UserNotFoundException;
import java51.useraccount.model.Role;
import java51.useraccount.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    final ModelMapper modelMapper;
    final UserRepository userRepository;

    @Override
    public UserDto registerUser(String baseUrl, NewUserDto newUserDto) {
        User user = modelMapper.map(newUserDto, User.class);
        user.setBaseUrl(baseUrl);
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto deleteUser(String baseUrl, String login) {
        User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(String baseUrl, String login, UpdateUserDto updateUserDto) {
        User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);

        String firstName = updateUserDto.getFirstName();
        if (firstName != null) user.setFirstName(firstName);

        String lastName = updateUserDto.getLastName();
        if (lastName != null) user.setLastName(lastName);

        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUser(String baseUrl, String login) {
        User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public RoleDto addRole(String baseUrl, String login, String role) {
        User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        if(user.addRole(role)) userRepository.save(user);
        return modelMapper.map(user, RoleDto.class);
    }

    @Override
    public RoleDto deleteRole(String baseUrl, String login, String role) {
        User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        if (user.removeRole(role)) userRepository.save(user);
        return modelMapper.map(user, RoleDto.class);
    }
}
