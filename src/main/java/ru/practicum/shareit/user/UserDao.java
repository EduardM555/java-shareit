package ru.practicum.shareit.user;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface UserDao {
    Map<Long, User> getUsers();
    List<User> getAll();

    User createUser(User user);

    User updateUser(Long id, User user);

    User getUserById(Long userId);

    void deleteUser(Long userId);
}
