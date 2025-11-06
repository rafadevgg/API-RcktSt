package br.com.rafadevgg.todolist.dto.response;

import java.time.LocalDateTime;

public record TaskResponseDTO(

        Long id,
        String title,
        String description,
        LocalDateTime dateStart,
        LocalDateTime dateEnd,
        String priority,
        LocalDateTime dateCreation

) {
}
