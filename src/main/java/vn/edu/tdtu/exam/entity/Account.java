package vn.edu.tdtu.exam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "workplace")
    private String workplace;

    @Column(name = "phone")
    private String phone;

    @Column(name = "dob")
    private LocalDate DoB;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive;

    @Column(name = "role")
    private String role;
    public Account(String email, String password, String name, String address, String workplace, String phone, LocalDate doB, String avatar, Boolean isActive, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.workplace = workplace;
        this.phone = phone;
        this.DoB = doB;
        this.avatar = avatar;
        this.isActive = true;
        this.role = role;
    }
}
