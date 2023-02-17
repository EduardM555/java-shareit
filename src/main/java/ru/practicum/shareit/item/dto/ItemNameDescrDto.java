package ru.practicum.shareit.item.dto;

import lombok.*;

/**
 * TODO Sprint add-controllers.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemNameDescrDto {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
}