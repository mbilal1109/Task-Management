package com.example.taskmanagement.services;

import com.example.taskmanagement.dtos.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(int userId, UserDto userDto);
    void deleteUser(int userId);
    List<UserDto> getAllUsers();
    UserDto getUserById(int userId);
}
