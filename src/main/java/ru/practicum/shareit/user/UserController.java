package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserForUpdateDto;
import ru.practicum.shareit.user.exception.UserCreateException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto) throws UserCreateException {
        return ResponseEntity.ok().body(userService.createUser(userDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserForUpdateDto> update(@PathVariable Long id, @Valid @RequestBody UserForUpdateDto userDto) {
        return ResponseEntity.ok().body(userService.updateUser(id, userDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getByUserId(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(userService.getByUserId(id));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
    }
}
