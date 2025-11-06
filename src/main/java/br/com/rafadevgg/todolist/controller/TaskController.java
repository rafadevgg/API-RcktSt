package br.com.rafadevgg.todolist.controller;

import br.com.rafadevgg.todolist.dto.request.TaskRequestDTO;
import br.com.rafadevgg.todolist.dto.response.TaskResponseDTO;
import br.com.rafadevgg.todolist.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody @Valid TaskRequestDTO taskRequest) {
        var create = taskService.createTask(taskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }
}
