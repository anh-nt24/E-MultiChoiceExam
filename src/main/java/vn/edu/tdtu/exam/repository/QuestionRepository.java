package vn.edu.tdtu.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.exam.entity.Exam;
import vn.edu.tdtu.exam.entity.ExamPaper;
import vn.edu.tdtu.exam.entity.Question;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {



    List<Question> findAllByExamPaper(ExamPaper examPaper);
}
