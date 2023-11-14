package vn.edu.tdtu.exam.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "teacher")
public class Teacher extends Account {
    @Column(name = "teacher_id", nullable = false, unique = true)
    private String teacherId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
}
