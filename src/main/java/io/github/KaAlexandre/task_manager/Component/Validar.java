package io.github.KaAlexandre.task_manager.Component;

import io.github.KaAlexandre.task_manager.Exceptions.TarefaIgualException;
import io.github.KaAlexandre.task_manager.Model.TaskEntity;
import io.github.KaAlexandre.task_manager.Repository.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Validar {
    private TaskRepository repository;
    private TarefaIgualException tarefaIgualException;

    public Validar(TaskRepository repository) {
        this.repository = repository;
    }

    public void ValidarTarefaAdicionar(TaskEntity tarefa) {
        if (repository.existsByNomeTarefa(tarefa.getNomeTarefa()) && repository.existsByDescricao(tarefa.getDescricao())) {
            throw new TarefaIgualException("A tarefa já foi cadastrada.");
        }
    }

    public void ValidarTarefaAtualizar(String id, TaskEntity tarefa) {
        Optional<TaskEntity> tarefaExistente = repository.findByNomeTarefa(tarefa.getNomeTarefa());
        if (tarefaExistente.isPresent() && !tarefaExistente.get().getId().equals(id)) {
            throw new TarefaIgualException("A tarefa já foi cadastrada.");
        }
    }

}
