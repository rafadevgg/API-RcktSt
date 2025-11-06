package br.com.rafadevgg.todolist.repository;

import br.com.rafadevgg.todolist.entity.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {
}
