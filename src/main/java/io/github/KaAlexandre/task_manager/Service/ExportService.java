package io.github.KaAlexandre.task_manager.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.KaAlexandre.task_manager.Model.TaskEntity;
import io.github.KaAlexandre.task_manager.Repository.TaskRepository;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Service
public class ExportService {

    private TaskRepository taskRepository;

    public ExportService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PreDestroy
    public void exportToJSON() {
        List<TaskEntity> tarefaEntities = taskRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File("tarefas.json"), tarefaEntities);
            System.out.println("Dados exportados para tarefas.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
