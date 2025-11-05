package br.com.rafadevgg.todolist.controller;

import br.com.rafadevgg.todolist.dto.request.UserRequestDTO;
import br.com.rafadevgg.todolist.dto.response.UserResponseDTO;
import br.com.rafadevgg.todolist.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid
                                                      UserRequestDTO userRequest) {
        var create = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

}
