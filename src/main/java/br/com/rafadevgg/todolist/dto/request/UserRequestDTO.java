package br.com.rafadevgg.todolist.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(

        @NotBlank(message = "É preciso informar um username!")
        String username,

        @NotBlank(message = "É preciso informar um name!")
        String name,

        @NotBlank(message = "É preciso informar um password!")
        String password

) {
}
