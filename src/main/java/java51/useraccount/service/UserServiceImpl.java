package java51.useraccount.service;

import java51.useraccount.dao.UserRepository;
import java51.useraccount.dto.NewUserDto;
import java51.useraccount.dto.RoleDto;
import java51.useraccount.dto.UpdateUserDto;
import java51.useraccount.dto.UserDto;
import java51.useraccount.dto.exception.UserNotFoundException;
import java51.useraccount.model.User;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    final ModelMapper modelMapper;
    final UserRepository userRepository;

    @Override
    public UserDto registerUser(NewUserDto newUserDto) {
        User user = modelMapper.map(newUserDto, User.class);
        String password = BCrypt.hashpw(newUserDto.getPassword(), BCrypt.gensalt());
        user.setPassword(password);
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto deleteUser(String login) {
        User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(String login, UpdateUserDto updateUserDto) {
        User user = userRepository.findByLogin(login).orElseThrow(UserNotFoundException::new);  //TODO: использовать FindById если поиск по id
        // todo или открыть опшнл

        String firstName = updateUserDto.getFirstName();
        if (firstName != null) user.setFirstName(firstName);

        String lastName = updateUserDto.getLastName();
        if (lastName != null) user.setLastName(lastName);

        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUser(String login) {
        User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public RoleDto addRole(String login, String role) {
        User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        if(user.addRole(role)) userRepository.save(user);
        return modelMapper.map(user, RoleDto.class);
    }

    @Override
    public RoleDto deleteRole(String login, String role) {
        User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        if (user.removeRole(role)) userRepository.save(user);
        return modelMapper.map(user, RoleDto.class);
    }

    @Override
    public void changePassword(String login, String newPassword) {
        User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        String password = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        user.setPassword(password);
        userRepository.save(user);
    }
}
