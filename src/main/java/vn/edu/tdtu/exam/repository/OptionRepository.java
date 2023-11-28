package vn.edu.tdtu.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.exam.entity.Option;
import vn.edu.tdtu.exam.entity.Question;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
}