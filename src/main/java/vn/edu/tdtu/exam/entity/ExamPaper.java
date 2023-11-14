package vn.edu.tdtu.exam.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "exam_paper")
public class ExamPaper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "access_token", nullable = true)
    private String accessToken;

    @Column(name = "times_allowed", nullable = false)
    private Integer timesAllowed;

    @Column(name = "showScore", nullable = false)
    private Boolean showScore;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
