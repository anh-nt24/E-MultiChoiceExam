package vn.edu.tdtu.exam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.exam.entity.Account;
import vn.edu.tdtu.exam.entity.ExamPaper;

import java.util.List;

@Repository
public interface ExamPaperRepository extends JpaRepository<ExamPaper, Long> {
    Page<ExamPaper> findBySubjectIdAndIsActiveTrue(Long subjectId, Pageable pageable);

    List<ExamPaper> findBySubjectIdAndIsActiveTrue(Long subjectId);

    @Query("SELECT e.teacher.id FROM ExamPaper e WHERE e.id = :examPaperId")
    Long findTeacherIdByExamPaperId(@Param("examPaperId") Long examPaperId);

    ExamPaper findExamPaperByExamId(Long id);
}
