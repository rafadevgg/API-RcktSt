package br.com.rafadevgg.todolist.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record TaskRequestDTO(

        @NotBlank(message = "É preciso informar um título para a tarefa!")
        @Size(max = 20 ,message = "O máximo de caracteres é 20!")
        String title,

        @NotBlank(message = "É preciso informar uma breve descrição para a tarefa!")
        @Size(max = 100, message = "O máximo de caracteres é 100!")
        String description,

        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @NotNull(message = "É preciso informar a data de início da tarefa!")
        LocalDateTime dateStart,

        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @NotNull(message = "É preciso informar a data do fim da tarefa!")
        LocalDateTime dateEnd,

        @NotBlank(message = "É preciso informar a prioridade da tarefa!")
        String priority

) {
}
