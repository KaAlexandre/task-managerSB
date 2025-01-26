package io.github.KaAlexandre.task_manager.Service;

import io.github.KaAlexandre.task_manager.Component.Message;
import io.github.KaAlexandre.task_manager.Component.Validar;
import io.github.KaAlexandre.task_manager.Exceptions.TarefaDeletadaException;
import io.github.KaAlexandre.task_manager.Exceptions.TarefaIgualException;
import io.github.KaAlexandre.task_manager.Model.TaskEntity;
import io.github.KaAlexandre.task_manager.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository repository;
    private final Validar validar;

    @Autowired
    public TaskService(TaskRepository repository, Validar validar) {
        this.repository = repository;
        this.validar = validar;
    }

    public Optional<TaskEntity> obterTarefasPorId(String id) {
        if (!tarefaExiste(id)) {
            throw new IllegalArgumentException("Tarefa não encontrada");
        }
        return repository.findById(id);
    }

    public TaskEntity adicionarTarefa(TaskEntity taskEntity) {
        validar.ValidarTarefaAdicionar(taskEntity);

        taskEntity.setId(UUID.randomUUID().toString());
        return repository.save(taskEntity);
    }

    public void deletarTarefa(String id) {
        if (!tarefaExiste(id)) {
            throw new TarefaDeletadaException("Tarefa não encontrada para exclusão");
        }
        repository.deleteById(id);
    }


    public boolean tarefaExiste(String id) {
        return repository.existsById(id);
    }

    public ResponseEntity<String> atualizarTarefa(String id, TaskEntity taskEntity) {
        try {
            validar.ValidarTarefaAtualizar(id, taskEntity);

            taskEntity.setId(id);
            repository.save(taskEntity);
            return new ResponseEntity<>("Tarefa atualizada com sucesso.", HttpStatus.OK);

        } catch (TarefaIgualException e) {
            return new ResponseEntity<>(
                    "Tarefa já existe com ID: " + e.getId() + ". Use este ID para atualizar.",
                    HttpStatus.CONFLICT
            );
        }
    }

}