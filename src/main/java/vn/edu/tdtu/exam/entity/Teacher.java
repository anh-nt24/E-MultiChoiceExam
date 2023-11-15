package vn.edu.tdtu.exam.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "teacher")
public class Teacher extends Account {
    @Column(name = "teacher_id", nullable = false, unique = true)
    private String teacherId;
}
