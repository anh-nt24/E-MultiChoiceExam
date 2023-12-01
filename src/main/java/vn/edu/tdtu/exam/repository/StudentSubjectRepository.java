package vn.edu.tdtu.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.exam.entity.StudentSubject;

import java.util.List;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Long> {
    List<StudentSubject> findByBanned(Boolean banned);

    @Query("SELECT s FROM StudentSubject s WHERE s.subject.name = :name AND s.banned = false")
    List<StudentSubject> searchBySubjectName(@Param("name") String name);
}
