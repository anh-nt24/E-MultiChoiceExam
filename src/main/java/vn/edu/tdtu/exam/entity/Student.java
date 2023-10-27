package vn.edu.tdtu.exam.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "student")
public class Student extends Account implements Serializable {
    @Column(name = "student_id", nullable = false, unique = true)
    private String studentId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "gender")
    private String gender;
}
