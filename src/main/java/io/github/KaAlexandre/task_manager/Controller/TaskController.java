package io.github.KaAlexandre.task_manager.Controller;

import io.github.KaAlexandre.task_manager.Component.Validar;
import io.github.KaAlexandre.task_manager.Model.TaskEntity;
import io.github.KaAlexandre.task_manager.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private Validar validar;

    @PostMapping("/adicionar")
    public TaskEntity adicionar(@RequestBody TaskEntity taskEntity) {
        validar.ValidarTarefaAdicionar(taskEntity);
        return taskService.adicionarTarefa(taskEntity);
    }

    @GetMapping("/{id}")
    public Optional<TaskEntity> procurarTarefaPorId(@PathVariable("id") String id) {
        return taskService.obterTarefasPorId(id);
    }
    @GetMapping("/listAll")
    public ResponseEntity<List<TaskEntity>> getAllTarefas() {
        return ResponseEntity.ok(taskService.listarTodasTarefas());
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deletarTodasTarefas() {
        taskService.deletarTodasTarefas();
        return ResponseEntity.ok("Todas as tarefas foram deletadas com sucesso.");
    }
    @DeleteMapping("/{id}")
    public void deleteTarefa(@PathVariable String id) {
        taskService.deletarTarefa(id);
    }

    @PutMapping("/tarefa/{id}")
    public ResponseEntity<String> atualizar(@PathVariable("id") String id, @RequestBody TaskEntity taskEntity) {
        validar.ValidarTarefaAtualizar(id,taskEntity);
        return taskService.atualizarTarefa(id, taskEntity);
    }
}
