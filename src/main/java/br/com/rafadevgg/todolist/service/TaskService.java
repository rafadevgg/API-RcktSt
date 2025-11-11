package br.com.rafadevgg.todolist.service;

import br.com.rafadevgg.todolist.dto.request.TaskRequestDTO;
import br.com.rafadevgg.todolist.dto.response.TaskResponseDTO;
import br.com.rafadevgg.todolist.entity.TaskModel;
import br.com.rafadevgg.todolist.entity.UserModel;
import br.com.rafadevgg.todolist.exception.InvalidDateException;
import br.com.rafadevgg.todolist.exception.ResourceNotFoundException;
import br.com.rafadevgg.todolist.exception.UnauthorizedException;
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
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));

        if (taskRequest.dateStart().isAfter(taskRequest.dateEnd())) {
            throw new InvalidDateException("A data de início não pode ser posterior à data de término!");
        }

        if (taskRequest.dateStart().isBefore(LocalDateTime.now())) {
            throw new InvalidDateException("A data de início não pode ser no passado!");
        }

        TaskModel task = new TaskModel();
        task.setTitle(taskRequest.title());
        task.setDescription(taskRequest.description());
        task.setDateStart(taskRequest.dateStart());
        task.setDateEnd(taskRequest.dateEnd());
        task.setPriority(taskRequest.priority());
        task.setUser(user);

        taskRepository.save(task);

        return convertToResponseDTO(task);
    }

    @Transactional
    public TaskResponseDTO getTaskById(Long id, HttpServletRequest request) {

        Long idUser = (Long) request.getAttribute("idUser");

        TaskModel task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada!"));

        if (!task.getUser().getId().equals(idUser)) {
            throw new UnauthorizedException("Você não tem permissão para acessar esta tarefa!");
        }

        return convertToResponseDTO(task);
    }

    @Transactional
    public List<TaskResponseDTO> listAllTasksByUser(HttpServletRequest request) {

        Long idUser = (Long) request.getAttribute("idUser");

        userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));

        List<TaskModel> tasks = taskRepository.findByUserId(idUser);

        return tasks.stream().map(this::convertToResponseDTO).toList();
    }

    @Transactional
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequest, HttpServletRequest request) {

        Long idUser = (Long) request.getAttribute("idUser");

        userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));

        TaskModel task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada!"));

        if (!task.getUser().getId().equals(idUser)) {
            throw new UnauthorizedException("Você não tem permissão para atualizar esta tarefa!");
        }

        if (taskRequest.dateStart() != null && taskRequest.dateEnd() != null) {
            if (taskRequest.dateStart().isAfter(taskRequest.dateEnd())) {
                throw new InvalidDateException("A data de início não pode ser posterior à data de término!");
            }
        }

        if (taskRequest.title() != null && !taskRequest.title().isBlank()) {
            task.setTitle(taskRequest.title());
        }
        if (taskRequest.description() != null && !taskRequest.description().isBlank()) {
            task.setDescription(taskRequest.description());
        }
        if (taskRequest.priority() != null && !taskRequest.priority().isBlank()) {
            task.setPriority(taskRequest.priority());
        }
        if (taskRequest.dateStart() != null) {
            task.setDateStart(taskRequest.dateStart());
        }
        if (taskRequest.dateEnd() != null) {
            task.setDateEnd(taskRequest.dateEnd());
        }

        taskRepository.save(task);

        return convertToResponseDTO(task);
    }

    private TaskResponseDTO convertToResponseDTO(TaskModel task) {
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