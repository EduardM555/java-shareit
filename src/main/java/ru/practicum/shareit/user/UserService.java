package ru.practicum.shareit.user;

import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserForUpdateDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto createUser(UserDto userDto);

    UserForUpdateDto updateUser(Long id, UserForUpdateDto userDto);

    UserDto getByUserId(Long userId);

    User getUser(Long userId);

    void deleteUser(Long userId);
}
