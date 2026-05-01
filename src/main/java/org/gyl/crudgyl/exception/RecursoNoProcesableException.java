package org.gyl.crudgyl.exception;

public class RecursoNoProcesableException extends RuntimeException {
    public RecursoNoProcesableException(String message) {
        super(message);
    }
    public RecursoNoProcesableException(Long id) {super("No se pudo procesar el elemento con ID: " + id + " ");}
}
