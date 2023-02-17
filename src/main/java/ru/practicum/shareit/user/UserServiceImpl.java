package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserForUpdateDto;
import ru.practicum.shareit.user.exception.UserCreateException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.exception.UserNotFoundException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.getAll();
        List<UserDto> usersDto = UserMapper.toListUserDto(users);
        return usersDto;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.forCreateUserFromDto(userDto);
        isEmailNotExists(user);
        userDto = UserMapper.toUserDto(userDao.createUser(user));
        return userDto;
    }

    @Override
    public UserForUpdateDto updateUser(Long id, UserForUpdateDto userDto) {
        User user = getUser(id);
        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        if (userDto.getEmail() != null) {
            if (!user.getEmail().equals(userDto.getEmail())) {
                isEmailNotExists(UserMapper.toUser(id, userDto));
            }
            user.setEmail(userDto.getEmail());
        }
        return UserMapper.toUserForUpdateDto(id, userDao.updateUser(id, user));
    }

    @Override
    public UserDto getByUserId(Long userId) {
        UserDto userDto = UserMapper.toUserDto(getUser(userId));
        return userDto;
    }

    @Override
    public void deleteUser(Long userId) {
        getUser(userId);
        userDao.deleteUser(userId);
    }

    private boolean isEmailNotExists(User user) {
        if (userDao.getAll().stream()
                .anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
            throw new UserCreateException("Ошибка валидации пользователя при создании: email существует");
        }
        return true;
    }

    public User getUser(Long userId) {
        User user = userDao.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("Пользователя в базе нет");
        }
        return user;
    }
}
