package io.github.KaAlexandre.task_manager.Service;

import io.github.KaAlexandre.task_manager.Component.Message;
import io.github.KaAlexandre.task_manager.Component.Validar;
import io.github.KaAlexandre.task_manager.Exceptions.TarefaAtualizadaException;
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


    @Autowired
    private TaskRepository repository;
    private Validar validar;
    private Message message;

    public TaskService(TaskRepository repository, Validar validar, Message message) {
        this.repository = repository;
        this.validar = validar;
        this.message = message;
    }

    public Optional<TaskEntity> obterTarefasPorId(String id) {
        if (!tarefaExiste(id)) {
            throw new IllegalArgumentException("Tarefa não encontrada");
        }
        return repository.findById(id);
    }

    public TaskEntity adicionarTarefa(TaskEntity taskEntity) {

        validar.ValidarTarefaAdicionar(taskEntity);

        if (repository.findByNomeTarefa(taskEntity.getNomeTarefa()).isPresent()) {
            throw new TarefaIgualException("A tarefa já foi cadastrada.");
        }


        var id = UUID.randomUUID().toString();
        taskEntity.setId(id);


        return repository.save(taskEntity);
    }


    public boolean tarefaJaCadastrada(TaskEntity taskEntity) {
        return repository.findByNomeTarefa(taskEntity.getNomeTarefa()).isPresent();
    }

    public boolean tarefaExistePorNome(String nomeTarefa) {
        return repository.existsByNomeTarefa(nomeTarefa);
    }

    public boolean tarefaExiste(String id) {

        return repository.existsById(id);
    }

    public void deletarTarefa(String id) {
        if (id == null || !tarefaExiste(id)) {
            throw new TarefaDeletadaException();
        }
        repository.deleteById(id);
    }

    public ResponseEntity<String> atualizarTarefa(String id, TaskEntity taskEntity) {
        Optional<TaskEntity> tarefaExistente = repository.findById(id);

        if (tarefaExistente.isEmpty()) {
            return new ResponseEntity<>("A tarefa não foi encontrada.", HttpStatus.NOT_FOUND);
        }

        // Verifica se já existe uma tarefa com o mesmo nome e descrição (exceto ela mesma)
        Optional<TaskEntity> tarefaComMesmoNomeEDescricao = repository.findByNomeTarefa(taskEntity.getNomeTarefa());
        if (tarefaComMesmoNomeEDescricao.isPresent() && !tarefaComMesmoNomeEDescricao.get().getId().equals(id)) {
            return new ResponseEntity<>("A tarefa já foi cadastrada.", HttpStatus.CONFLICT);
        }

        taskEntity.setId(id);
        repository.save(taskEntity);

        return new ResponseEntity<>("Tarefa atualizada com sucesso.", HttpStatus.OK);
    }
}
