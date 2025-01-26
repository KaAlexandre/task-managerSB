package io.github.KaAlexandre.task_manager.Component;

import org.springframework.stereotype.Component;

@Component
public class Message {
    public void message(String mensagem) {
        System.out.println(mensagem);
    }

    public String addTroubleMessage(String mensagem) {
        return "Essa tarefa já foi cadastrada: " + mensagem;
    }
}
