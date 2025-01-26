package io.github.KaAlexandre.task_manager.Repository;

import io.github.KaAlexandre.task_manager.Model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, String> {
    boolean existsByNomeTarefa(String nome);

    boolean existsByDescricao(String descricao);

    Optional<TaskEntity> findByNomeTarefa(String nomeTarefa);
}
