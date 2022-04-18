package org.boip.util.countryservice.rest;

import lombok.extern.slf4j.Slf4j;
import org.boip.util.countryservice.exception.ApplicationException;
import org.boip.util.countryservice.exception.NotFoundException;
import org.boip.util.countryservice.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public abstract class AbstractController {

    @ExceptionHandler
    protected void handleValidationException(ValidationException e, HttpServletResponse response) throws IOException {
        log.warn("Validation failed for: {}", e.getMessagesAsString());
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessagesAsString());
    }

    @ExceptionHandler
    protected void handleNotFoundException(NotFoundException e, HttpServletResponse response) throws IOException {
        log.info("NotFoundException: {}", e.getMessage());
        response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    // in case of http 500 internal server error, no information about the exception is to be revealed, so a different approach is used.
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handle(ApplicationException e, HttpServletRequest request, HttpServletResponse response) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }

}
