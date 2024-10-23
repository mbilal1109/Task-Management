package com.example.taskmanagement.services.impls;

import com.example.taskmanagement.dtos.UserDto;
import com.example.taskmanagement.entities.User;
import com.example.taskmanagement.repositories.UserRepository;
import com.example.taskmanagement.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userRepository.save(mapper.map(userDto, User.class));
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(int userId, UserDto userDto) {
        User currentUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with given id not found"));

        currentUser.setName(userDto.getName());
        User user = userRepository.save(currentUser);

        return mapper.map(user, UserDto.class);
    }

    @Override
    public void deleteUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with given id not found"));
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> mapper.map(user, UserDto.class)).toList();
        return userDtos;
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with given id not found"));
        return mapper.map(user, UserDto.class);
    }
}
