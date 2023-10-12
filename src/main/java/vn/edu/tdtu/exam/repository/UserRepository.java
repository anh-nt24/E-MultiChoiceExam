package vn.edu.tdtu.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.exam.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
