package org.boip.util.countryservice.rest;

import lombok.extern.slf4j.Slf4j;
import org.boip.util.countryservice.exception.ApplicationException;
import org.boip.util.countryservice.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    protected void handleApplicationException(ApplicationException e, HttpServletResponse response) throws IOException {
        log.error(e.getMessage(), e);
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "");
    }

}
