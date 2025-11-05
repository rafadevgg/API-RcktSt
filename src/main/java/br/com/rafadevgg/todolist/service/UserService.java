package br.com.rafadevgg.todolist.service;

import br.com.rafadevgg.todolist.dto.request.UserRequestDTO;
import br.com.rafadevgg.todolist.dto.response.UserResponseDTO;
import br.com.rafadevgg.todolist.entity.UserModel;
import br.com.rafadevgg.todolist.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequest) {

        var userModel = userRepository.findByUsername(userRequest.name());

        if(userModel != null) {
            throw new RuntimeException("Usuário já existente!");
        }

        UserModel user = new UserModel();

        user.setUsername(userRequest.username());
        user.setName(userRequest.name());
        user.setPassword(userRequest.password());

        userRepository.save(user);

        return new UserResponseDTO(

                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getPassword(),
                user.getDateCreation()

        );

    }

}
