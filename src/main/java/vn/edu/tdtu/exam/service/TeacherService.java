package vn.edu.tdtu.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.exam.dto.AccountDTO;
import vn.edu.tdtu.exam.entity.Account;
import vn.edu.tdtu.exam.entity.Teacher;
import vn.edu.tdtu.exam.repository.AccountRepository;
import vn.edu.tdtu.exam.repository.TeacherRepository;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    public Boolean saveTeacher(Teacher teacher) {
        try {
            teacherRepository.save(teacher);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Teacher getTeacher(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

}
