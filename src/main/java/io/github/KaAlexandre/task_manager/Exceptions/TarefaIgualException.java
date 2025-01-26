package io.github.KaAlexandre.task_manager.Exceptions;

public class TarefaIgualException extends RuntimeException {
    private final String id;

    public TarefaIgualException(String id) {
        super("Tarefa duplicada. ID existente: " + id);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
