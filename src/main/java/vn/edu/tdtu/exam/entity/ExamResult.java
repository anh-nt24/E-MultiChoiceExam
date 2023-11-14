package vn.edu.tdtu.exam.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "exam_result")
public class ExamResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exampaper_id")
    private ExamPaper examPaper;

    @Column(name = "score")
    private Double score;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "taken_time")
    private Integer takenTime;

    @Column(name = "attempt")
    private Integer attempt;
}
