package br.com.rafadevgg.todolist.repository;

import br.com.rafadevgg.todolist.entity.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {

    List<TaskModel> findByUserId(Long userId);

}
