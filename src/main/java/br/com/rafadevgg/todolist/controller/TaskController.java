package br.com.rafadevgg.todolist.controller;

import br.com.rafadevgg.todolist.dto.request.TaskRequestDTO;
import br.com.rafadevgg.todolist.dto.response.TaskResponseDTO;
import br.com.rafadevgg.todolist.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody @Valid TaskRequestDTO taskRequest,
                                                      HttpServletRequest request) {
        var create = taskService.createTask(taskRequest, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskResponseDTO> listTask(@PathVariable Long id,
                                                    HttpServletRequest request) {
        var list = taskService.getTaskById(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> listAllTasksByUser(HttpServletRequest request) {
        var listAll = taskService.listAllTasksByUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(listAll);
    }
}
