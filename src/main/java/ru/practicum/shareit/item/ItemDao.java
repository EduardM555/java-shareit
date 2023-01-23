package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.Map;

public interface ItemDao {
    Map<Long, List<Item>> getItems();
    Item addItem(Long userId, Item item);

    Item updateItem(Long userId, Long itemId, Item item);
}
