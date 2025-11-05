package br.com.rafadevgg.todolist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class UserModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb_id")
    private Long id;

    @Column(name = "tb_username")
    private String username;

    @Column(name = "tb_name")
    private String name;

    @Column(name = "tb_password")
    private String password;

    @CreationTimestamp
    @Column(name = "tb_criacao")
    private LocalDateTime dateCreation;

}
