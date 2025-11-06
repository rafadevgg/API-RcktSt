package br.com.rafadevgg.todolist.dto.response;

import java.time.LocalDateTime;

public record UserResponseDTO(

        Long id,
        String username,
        String name,
        LocalDateTime dateCreation

) {
}
