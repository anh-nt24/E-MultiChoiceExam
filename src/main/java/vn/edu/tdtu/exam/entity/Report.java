package vn.edu.tdtu.exam.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "exampaper_id")
    private ExamPaper examPaper;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}

