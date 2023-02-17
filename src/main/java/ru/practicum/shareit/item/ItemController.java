package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemNameDescrDto;
import ru.practicum.shareit.item.dto.ItemUpdateDto;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemDto> addItem(@RequestHeader("X-Sharer-User-Id") Long userId,
                                           @Valid @RequestBody ItemDto itemDto) {
        return ResponseEntity.ok().body(itemService.addItem(userId, itemDto));
    }

    @PatchMapping("{itemId}")
    public ResponseEntity<ItemUpdateDto> updateItem(@RequestHeader("X-Sharer-User-Id") Long userId,
                                    @PathVariable(value = "itemId") Long itemId,
                                    @Valid @RequestBody ItemUpdateDto itemUpdateDto) {
        return ResponseEntity.ok().body(itemService.updateItem(userId, itemId, itemUpdateDto));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDto> getItem(@PathVariable(value = "itemId") Long itemId) {
        return ResponseEntity.ok().body(itemService.getItemDto(itemId));
    }

    @GetMapping
    public ResponseEntity<List<ItemNameDescrDto>> getAllUserItems(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return ResponseEntity.ok().body(itemService.getAllUserItems(userId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemUpdateDto>> searchItem(@RequestParam(value = "text", required = false) String text) {
        return ResponseEntity.ok().body(itemService.searchItem(text));
    }
}
