package vn.edu.tdtu.exam.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "admin")
public class Admin extends Account implements Serializable {
    @Column(name = "admin_name")
    private String adminName;

}
