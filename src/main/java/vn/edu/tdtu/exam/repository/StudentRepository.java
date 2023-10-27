package vn.edu.tdtu.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.exam.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
