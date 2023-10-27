package vn.edu.tdtu.exam.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Data
public class Account{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
