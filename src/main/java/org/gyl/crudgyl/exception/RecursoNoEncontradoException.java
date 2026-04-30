package org.gyl.crudgyl.exception;

public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException(String message) {
        super(message);
    }
    public RecursoNoEncontradoException(Long id) {super("No se encontró el ID: " + id);}
}
