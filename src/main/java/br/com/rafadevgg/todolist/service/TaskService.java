package br.com.rafadevgg.todolist.service;

import br.com.rafadevgg.todolist.dto.request.TaskRequestDTO;
import br.com.rafadevgg.todolist.dto.response.TaskResponseDTO;
import br.com.rafadevgg.todolist.entity.TaskModel;
import br.com.rafadevgg.todolist.entity.UserModel;
import br.com.rafadevgg.todolist.repository.TaskRepository;
import br.com.rafadevgg.todolist.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public TaskResponseDTO createTask(TaskRequestDTO taskRequest, HttpServletRequest request) {

        Long idUser = (Long) request.getAttribute("idUser");

        UserModel user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        TaskModel task = new TaskModel();
        task.setTitle(taskRequest.title());
        task.setDescription(taskRequest.description());
        task.setDateStart(taskRequest.dateStart());
        task.setDateEnd(taskRequest.dateEnd());
        task.setPriority(taskRequest.priority());
        task.setUser(user);

        taskRepository.save(task);

        return new TaskResponseDTO(

                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDateStart(),
                task.getDateEnd(),
                task.getPriority(),
                task.getDateCreation(),
                task.getUser().getId()

        );

    }

    @Transactional
    public List<TaskResponseDTO> listTask(Long id, HttpServletRequest request) {

        Long idUser = (Long) request.getAttribute("idUser");

        UserModel user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        TaskModel task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!"));

        return new TaskResponseDTO(

                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDateStart(),
                task.getDateEnd(),
                task.getPriority(),
                task.getDateCreation(),
                task.getUser().getId()
        );

    }
}
