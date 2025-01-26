package io.github.KaAlexandre.task_manager;

import io.github.KaAlexandre.task_manager.Service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManagerApplication {
	@Autowired
	ExportService exportService;

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

}
