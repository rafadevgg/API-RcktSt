package br.com.rafadevgg.todolist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class TaskModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb_id")
    private Long id;

    @Column(name = "tb_titulo")
    private String title;

    @Column(name = "tb_descricao")
    private String description;

    @Column(name = "dateStart")
    private LocalDateTime dateStart;

    @Column(name = "dateEnd")
    private LocalDateTime dateEnd;

    @Column(name = "tb_prioridade")
    private String priority;

    @CreationTimestamp
    @Column(name = "tb_criacao")
    private LocalDateTime dateCreation;

    /*@JoinColumn(name = "idUser")
    private UserModel idUser;*/

}
