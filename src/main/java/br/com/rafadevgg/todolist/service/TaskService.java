package br.com.rafadevgg.todolist.service;

import br.com.rafadevgg.todolist.dto.request.TaskRequestDTO;
import br.com.rafadevgg.todolist.dto.response.TaskResponseDTO;
import br.com.rafadevgg.todolist.entity.TaskModel;
import br.com.rafadevgg.todolist.repository.TaskRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public TaskResponseDTO createTask(TaskRequestDTO taskRequest, HttpServletRequest request) {

        var idUser = request.getAttribute("idUser");
        taskRequest.setIdUser(idUser);

        TaskModel task = new TaskModel();

        task.setTitle(taskRequest.title());
        task.setDescription(taskRequest.description());
        task.setDateStart(taskRequest.dateStart());
        task.setDateEnd(taskRequest.dateEnd());
        task.setPriority(taskRequest.priority());

        taskRepository.save(task);

        return new TaskResponseDTO(

                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDateStart(),
                task.getDateEnd(),
                task.getPriority(),
                task.getDateCreation()

        );

    }
}
