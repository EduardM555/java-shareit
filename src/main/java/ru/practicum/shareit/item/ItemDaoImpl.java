package ru.practicum.shareit.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ItemDaoImpl implements ItemDao {
    private Map<Long, List<Item>> items = new HashMap<>();
    private static long id = 0;

    @Override
    public Map<Long, List<Item>> getItems() {
        return items;
    }

    @Override
    public Item addItem(Long userId, Item item) {
        item.setId(++id);
        items.compute(userId, (id, userItems) -> {
            if (userItems == null) {
                userItems = new ArrayList<>();
            }
            userItems.add(item);
            return userItems;
        });
        return items.get(userId).stream()
                .filter(i -> i.getId().equals(item.getId()))
                .findFirst()
                .get();
    }

    @Override
    public Item updateItem(Long userId, Long itemId, Item item) {
        items.get(userId).removeIf(i -> i.getId().equals(itemId));
        items.get(userId).add(item);
        return item;
    }
}
