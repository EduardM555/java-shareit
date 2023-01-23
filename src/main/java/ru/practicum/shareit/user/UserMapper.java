package ru.practicum.shareit.user;

import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserForUpdateDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static User forCreateUserFromDto(UserDto userDto) {
        return new User(userDto.getName(), userDto.getEmail());
    }

    public static List<UserDto> toListUserDto(List<User> users) {
        return users.stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public static User toUser(Long id, UserForUpdateDto userDto) {
        return new User(id, userDto.getName(), userDto.getEmail());
    }

    public static UserForUpdateDto toUserForUpdateDto(Long id, User user) {
        return new UserForUpdateDto(id, user.getName(), user.getEmail());
    }
}
