package org.gyl.crudgyl.exception.handler;

import org.gyl.crudgyl.exception.ClaveUnicaRepetidaException;
import org.gyl.crudgyl.exception.RecursoDesaparecidoException;
import org.gyl.crudgyl.exception.RecursoNoEncontradoException;
import org.gyl.crudgyl.exception.RecursoNoProcesableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<String> handleNotFound(RecursoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(ClaveUnicaRepetidaException.class)
    public ResponseEntity<String> handleRepeatedKey(ClaveUnicaRepetidaException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(RecursoDesaparecidoException.class)
    public ResponseEntity<String> handleUserNotFound(RecursoDesaparecidoException ex) {
        return ResponseEntity.status(HttpStatus.GONE)
                .body(ex.getMessage());
    }

    @ExceptionHandler(RecursoNoProcesableException.class)
    public ResponseEntity<String> handleNotProcessable(RecursoNoProcesableException ex){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAnything(Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al procesar los datos, verifique el contenido del método\n"
                + ex.getMessage());
    }
}