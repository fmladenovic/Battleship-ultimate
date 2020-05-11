package com.sbz.battleship.web.resolver;


import com.sbz.battleship.domain.exception.BadRequest;
import com.sbz.battleship.domain.exception.InternalServerError;
import com.sbz.battleship.domain.exception.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class Resolver extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<?> badRequestResolver(BadRequest ex) {
        ex.printStackTrace();
    	return new ResponseEntity<>(new ExceptionDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<?> notFoundResolver(NotFound ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new ExceptionDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<?> internalServerErrorResolver(InternalServerError ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new ExceptionDTO(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
