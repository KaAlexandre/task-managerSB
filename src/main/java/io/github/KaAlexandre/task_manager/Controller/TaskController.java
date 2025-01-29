package io.github.KaAlexandre.task_manager.Controller;

import io.github.KaAlexandre.task_manager.Component.Validar;
import io.github.KaAlexandre.task_manager.Model.TaskEntity;
import io.github.KaAlexandre.task_manager.Service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
@Tag(name = "Tarefas", description = "Endpoints para gerenciamento de tarefas")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private Validar validar;

    @PostMapping("/adicionar")
    @Operation(summary = "Adicionar uma nova tarefa", description = "Cria uma nova tarefa no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa criada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskEntity.class),
                            examples = @ExampleObject(value = """
                {
                    "id": "123e4567-e89b-12d3-a456-426614174000",
                    "nomeTarefa": "Estudar Spring Boot",
                    "estado": "Pendente",
                    "descricao": "Estudar Spring Boot para o projeto"
                }
            """))),
            @ApiResponse(responseCode = "409", description = "Conflito: Tarefa já existe",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = """
                "Tarefa já existe com ID: 123e4567-e89b-12d3-a456-426614174000. Use este ID para atualizar."
            """)))
    })
    public TaskEntity adicionar(@RequestBody TaskEntity taskEntity) {
        validar.ValidarTarefaAdicionar(taskEntity);
        return taskService.adicionarTarefa(taskEntity);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tarefa por ID", description = "Retorna uma tarefa específica pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskEntity.class),
                            examples = @ExampleObject(value = """
                {
                    "id": "123e4567-e89b-12d3-a456-426614174000",
                    "nomeTarefa": "Estudar Spring Boot",
                    "estado": "Pendente",
                    "descricao": "Estudar Spring Boot para o projeto"
                }
            """))),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = """
                "Tarefa não encontrada"
            """)))
    })
    public Optional<TaskEntity> procurarTarefaPorId(
            @Parameter(description = "ID da tarefa", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable("id") String id) {
        return taskService.obterTarefasPorId(id);
    }

    @GetMapping("/listAll")
    @Operation(summary = "Listar todas as tarefas", description = "Retorna uma lista de todas as tarefas cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskEntity.class),
                            examples = @ExampleObject(value = """
                [
                    {
                        "id": "123e4567-e89b-12d3-a456-426614174000",
                        "nomeTarefa": "Estudar Spring Boot",
                        "estado": "Pendente",
                        "descricao": "Estudar Spring Boot para o projeto"
                    },
                    {
                        "id": "123e4567-e89b-12d3-a456-426614174001",
                        "nomeTarefa": "Fazer compras",
                        "estado": "Concluído",
                        "descricao": "Comprar itens para a casa"
                    }
                ]
            """)))
    })
    public ResponseEntity<List<TaskEntity>> getAllTarefas() {
        return ResponseEntity.ok(taskService.listarTodasTarefas());
    }

    @DeleteMapping("/deleteAll")
    @Operation(summary = "Deletar todas as tarefas", description = "Remove todas as tarefas do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas as tarefas foram deletadas com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = """
                "Todas as tarefas foram deletadas com sucesso."
            """)))
    })
    public ResponseEntity<String> deletarTodasTarefas() {
        taskService.deletarTodasTarefas();
        return ResponseEntity.ok("Todas as tarefas foram deletadas com sucesso.");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma tarefa por ID", description = "Remove uma tarefa específica pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = """
                "Tarefa não encontrada para exclusão"
            """)))
    })
    public void deleteTarefa(
            @Parameter(description = "ID da tarefa", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable String id) {
        taskService.deletarTarefa(id);
    }

    @PutMapping("/tarefa/{id}")
    @Operation(summary = "Atualizar uma tarefa", description = "Atualiza os dados de uma tarefa existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = """
                "Tarefa atualizada com sucesso."
            """))),
            @ApiResponse(responseCode = "409", description = "Conflito: Tarefa já existe",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = """
                "Tarefa já existe com ID: 123e4567-e89b-12d3-a456-426614174000. Use este ID para atualizar."
            """)))
    })
    public ResponseEntity<String> atualizar(
            @Parameter(description = "ID da tarefa", example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable("id") String id,
            @RequestBody TaskEntity taskEntity) {
        validar.ValidarTarefaAtualizar(id, taskEntity);
        return taskService.atualizarTarefa(id, taskEntity);
    }
}