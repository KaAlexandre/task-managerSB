package io.github.KaAlexandre.task_manager.Exceptions;

public class TarefaDeletadaException extends RuntimeException {
    public TarefaDeletadaException() {
        super("Essa tarefa já foi deletada ou não existe no banco de dados.");
    }

    public TarefaDeletadaException(String message) {
        super(message);
    }
}
