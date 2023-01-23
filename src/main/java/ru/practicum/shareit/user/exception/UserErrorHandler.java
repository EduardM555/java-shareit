package ru.practicum.shareit.user.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("ru.practicum.shareit")
@Slf4j
public class UserErrorHandler {
    @ExceptionHandler(UserCreateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handlerValidationException(final UserCreateException e) {
        log.warn("409 {}", e.getMessage());
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerValidationException(final UserNotFoundException e) {
        log.warn("404 {}", e.getMessage());
        return new ErrorResponse(e.getMessage());
    }

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorResponse handlerThrowable(final Throwable e) {
//        log.error("500 {}, e: {}", e.getMessage(), e);
//        return new ErrorResponse("Произошла непредвиденная ошибка.");
//    }
}
