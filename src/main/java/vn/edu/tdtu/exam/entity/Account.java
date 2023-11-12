package vn.edu.tdtu.exam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
