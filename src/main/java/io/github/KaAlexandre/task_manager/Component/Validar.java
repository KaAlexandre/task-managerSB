package io.github.KaAlexandre.task_manager.Component;

import io.github.KaAlexandre.task_manager.Exceptions.TarefaIgualException;
import io.github.KaAlexandre.task_manager.Model.TaskEntity;
import io.github.KaAlexandre.task_manager.Repository.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Validar {
    private final TaskRepository repository;

    public Validar(TaskRepository repository) {
        this.repository = repository;
    }

    public void ValidarTarefaAdicionar(TaskEntity tarefa) {
        repository.findByNomeTarefa(tarefa.getNomeTarefa())
                .ifPresent(existing -> {
                    throw new TarefaIgualException(existing.getId());
                });
    }

    public void ValidarTarefaAtualizar(String id, TaskEntity tarefa) {
        repository.findByNomeTarefa(tarefa.getNomeTarefa())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new TarefaIgualException(existing.getId());
                    }
                });
    }
}