package co.com.perficient.project3.controller;

import jakarta.persistence.NonUniqueResultException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {

    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> constraintViolations(ConstraintViolationException ex) {
        logger.error(ex.getMessage());
        List<String> errors = ex.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage())
                .toList();
        return ResponseEntity.internalServerError().body(errors);
    }

    @ExceptionHandler(NonUniqueResultException.class)
    public ResponseEntity<Object> nonUniqueResult(NonUniqueResultException ex) {
        logger.error(ex.getMessage());
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }
}
