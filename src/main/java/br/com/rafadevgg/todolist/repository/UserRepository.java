package br.com.rafadevgg.todolist.repository;

import br.com.rafadevgg.todolist.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByUsername(String username);

}
