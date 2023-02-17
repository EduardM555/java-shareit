package ru.practicum.shareit.item.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.user.exception.ErrorResponse;

@RestControllerAdvice("ru.practicum.shareit")
@Slf4j
public class ItemErrorHandler {
    @ExceptionHandler(ItemAccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handlerValidationException(final ItemAccessException e) {
        log.warn("403 {}", e.getMessage());
        return new ErrorResponse(e.getMessage());
    }
}
