package ru.practicum.shareit.user;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
@Getter
@Setter
public class UserDaoImpl implements UserDao {
    private Map<Long, User> users = new HashMap<>();
    private static long id = 0;

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User createUser(User user) {
        user.setId(++id);
        users.put(user.getId(), user);
        return users.get(user.getId());
    }

    @Override
    public User updateUser(Long id, User user) {
        users.put(id, user);
        return users.get(id);
    }

    @Override
    public User getUserById(Long userId) {
        return users.getOrDefault(userId, null);
    }

    @Override
    public void deleteUser(Long userId) {
        users.remove(userId);
    }

    @Override
    public Map<Long, User> getUsers() {
        return users;
    }
}
