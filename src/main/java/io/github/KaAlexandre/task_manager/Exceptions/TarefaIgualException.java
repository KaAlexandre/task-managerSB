package io.github.KaAlexandre.task_manager.Exceptions;

public class TarefaIgualException extends RuntimeException {
    public TarefaIgualException() {
        super("Essa tarefa já foi cadastrada.");
    }

    public TarefaIgualException(String message) {
        super(message);
    }
}
