package ru.practicum.shareit.user.dto;

import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserForUpdateDto {
    private Long id;
    private String name;
    @Email
    private String email;
}
