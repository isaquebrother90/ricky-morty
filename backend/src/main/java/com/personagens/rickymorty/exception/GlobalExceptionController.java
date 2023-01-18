package com.personagens.rickymorty.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Date;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageExceptionHandler> handleAllUncaughtException(Exception e, WebRequest request) {
        log.error("An unknown error has occurred.", e);
        String type = URI.create("http://localhost:8080").toString();
        String title = "Internal Server Error";
        String detail = e.getMessage();
        if (detail == null) detail = e.toString();
        String instance = request.getDescription(false).substring(4);

        MessageExceptionHandler error = new MessageExceptionHandler(type, title, HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred: " + detail, instance, new Date());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<MessageExceptionHandler> handleUnauthorizedException(HttpClientErrorException.Forbidden e,
                                                                               WebRequest request) {
        log.error("Forbidden.", e);
        String type = URI.create("http://localhost:8080").toString();
        String title = "Forbidden!";
        String detail = e.getMessage();
        if (detail == null) detail = e.toString();
        String instance = request.getDescription(false).substring(4);

        MessageExceptionHandler error = new MessageExceptionHandler(type, title, HttpStatus.FORBIDDEN.value(),
                "Unable to complete authentication: " + detail, instance, new Date());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ContentTypeException.class)
    public ResponseEntity<MessageExceptionHandler> handleContentTypeException(ContentTypeException e,
                                                                              WebRequest request) {
        log.error("Invalid file extension", e);
        String type = URI.create("http://localhost:8080").toString();
        String title = "Bad request";
        String detail = e.getMessage();
        if (detail == null) detail = e.toString();
        String instance = request.getDescription(false).substring(4);

        MessageExceptionHandler error = new MessageExceptionHandler(type, title, HttpStatus.BAD_REQUEST.value(),
                "Extension not allowed. " + detail, instance, new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNoSuchElementFoundException(NoSuchElementFoundException elementNotFoundException, WebRequest request) {
        log.error("Element not Found", elementNotFoundException);
        String type = URI.create("http://localhost:8080").toString();
        String title = "Not found";
        String detail = elementNotFoundException.getMessage();
        if (detail == null) detail = elementNotFoundException.toString();
        String instance = request.getDescription(false).substring(4);

        MessageExceptionHandler error = new MessageExceptionHandler(type, title, HttpStatus.BAD_REQUEST.value(),
                "Extension not allowed. " + detail, instance, new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
