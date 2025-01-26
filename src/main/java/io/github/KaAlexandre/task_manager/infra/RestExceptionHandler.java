package io.github.KaAlexandre.task_manager.infra;

import io.github.KaAlexandre.task_manager.Exceptions.TarefaDeletadaException;
import io.github.KaAlexandre.task_manager.Exceptions.TarefaIgualException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.swing.*;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TarefaIgualException.class)
    public ResponseEntity<String> handleTarefaIgualException(TarefaIgualException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(TarefaDeletadaException.class)
    public ResponseEntity<String> handleTarefaDeletadaException(TarefaDeletadaException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}

