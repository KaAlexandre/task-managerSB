package io.github.KaAlexandre.task_manager.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Tarefas")
public class TaskEntity {

    @Id
    @Column
    private String id;

    @Column (name= "nome",unique = true)
    private String nomeTarefa;
    @Column
    private String estado;
    @Column
    private String descricao;

    public TaskEntity() {
    }

    public TaskEntity(String id, String nomeTarefa, String estado, String descricao) {
        this.id = id;
        this.nomeTarefa = nomeTarefa;
        this.descricao = descricao;
        this.estado = estado;
    }

    public TaskEntity(String estado, String id, String nomeTarefa) {
        this.estado = estado;
        this.id = id;
        this.nomeTarefa = nomeTarefa;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

