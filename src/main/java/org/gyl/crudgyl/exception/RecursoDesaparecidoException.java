package org.gyl.crudgyl.exception;

public class RecursoDesaparecidoException extends RuntimeException {
    public RecursoDesaparecidoException(String message) {
        super(message);
    }
    public RecursoDesaparecidoException(Long id) {super("El elemento con id: " + id + " fue encontrado pero desapareció antes de acceder");}
}
