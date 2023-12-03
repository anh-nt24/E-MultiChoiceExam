package vn.edu.tdtu.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.exam.entity.ExamPaper;
import vn.edu.tdtu.exam.entity.ExamResult;
import vn.edu.tdtu.exam.entity.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {

    List<ExamResult> findAllExamResultByStudent (Student student);
    Optional<ExamResult> findExamResultByIdAndStudent(Long id, Student student);
    List<ExamResult> findByExamPaperAndStudent(ExamPaper examPaper, Student student);
    Optional<ExamResult> findByExamPaperAndStudentAndAttempt(ExamPaper examPaper, Student student, Integer attempt);

}
