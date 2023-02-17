package ru.practicum.shareit.item;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemNameDescrDto;
import ru.practicum.shareit.item.dto.ItemUpdateDto;
import ru.practicum.shareit.item.exceptions.ItemAccessException;
import ru.practicum.shareit.item.exceptions.ItemNotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemDao itemDao;
    private final UserService userService;

    @Override
    public ItemDto addItem(Long userId, ItemDto itemDto) {
        Item item = ItemMapper.toItem(itemDto);
        User owner = userService.getUser(userId); // Проверка наличия в базе user
        item.setOwner(owner);
        return ItemMapper.toItemDto(itemDao.addItem(userId, item));
    }

    @Override
    public ItemUpdateDto updateItem(Long userId, Long itemId, ItemUpdateDto itemUpdateDto) {
        Item item = getItem(itemId);
        if (!item.getOwner().getId().equals(userId)) {
            throw new ItemAccessException("Доступ для редактирования запрещен");
        }
        if (itemUpdateDto.getName() != null) {
            item.setName(itemUpdateDto.getName());
        }
        if (itemUpdateDto.getDescription() != null) {
            item.setDescription(itemUpdateDto.getDescription());
        }
        if (itemUpdateDto.getAvailable() != null) {
            item.setAvailable(itemUpdateDto.getAvailable());
        }
        item = itemDao.updateItem(userId, itemId, item);
        return ItemMapper.toItemUpdateDto(item);
    }

    @Override
    public List<ItemNameDescrDto> getAllUserItems(Long userId) {
        userService.getUser(userId); // Валидация
        List<Item> items = itemDao.getItems().get(userId);
        return items.stream()
                .map(item -> new ItemNameDescrDto(
                        item.getId(),
                        item.getName(),
                        item.getDescription(),
                        item.getAvailable()))
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto getItemDto(Long itemId) {
        Item item = getItem(itemId);
        return ItemMapper.toItemDto(item);
    }

    @Override
    public Item getItem(Long itemId) {
        List<List<Item>> itemsLists = List.copyOf(itemDao.getItems().values());
        Item item = null;
        // Перебираем все предметы
        for (List<Item> items: itemsLists) {
            for (Item itm: items) {
                if (itm.getId().equals(itemId)) {
                    item = itm;
                }
            }
        }
        if (item == null) {
            throw new ItemNotFoundException("Предмета в базе нет");
        }
        return item;
    }

    @Override
    public List<ItemUpdateDto> searchItem(String text) {
        List<ItemUpdateDto> searchedItems = new ArrayList<>();
        if (text.isEmpty()) return searchedItems;
        String textLow = text.toLowerCase();
        List<List<Item>> itemsLists = List.copyOf(itemDao.getItems().values());
        // Перебираем все предметы для поиска
        for (List<Item> items: itemsLists) {
            for (Item itm: items) {
                if (itm.getAvailable() && (itm.getName().toLowerCase().contains(textLow)
                        || itm.getDescription().toLowerCase().contains(textLow))) {
                    searchedItems.add(ItemMapper.toItemUpdateDto(itm));
                }
            }
        }
        return searchedItems;
    }
}
