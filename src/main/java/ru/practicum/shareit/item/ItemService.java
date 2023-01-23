package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemNameDescrDto;
import ru.practicum.shareit.item.dto.ItemUpdateDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {
    ItemDto addItem(Long userId, ItemDto itemDto);

    ItemDto getItemDto(Long itemId);
    Item getItem(Long itemId);

    ItemUpdateDto updateItem(Long userId, Long itemId, ItemUpdateDto itemUpdateDto);

    List<ItemNameDescrDto> getAllUserItems(Long userId);

    List<ItemUpdateDto> searchItem(String text);
}
