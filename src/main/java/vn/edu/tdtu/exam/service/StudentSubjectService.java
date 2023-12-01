package vn.edu.tdtu.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.exam.entity.StudentSubject;
import vn.edu.tdtu.exam.repository.StudentRepository;
import vn.edu.tdtu.exam.repository.StudentSubjectRepository;

import java.util.Collections;
import java.util.List;

@Service
public class StudentSubjectService {
    private final StudentSubjectRepository studentSubjectRepository;
    SubjectService subjectService;
    @Autowired
    public StudentSubjectService(StudentSubjectRepository studentSubjectRepository) {
        this.studentSubjectRepository = studentSubjectRepository;
    }
    public List<StudentSubject> findByBanned(Boolean banned){
        return studentSubjectRepository.findByBanned(banned);
    }
    public List<StudentSubject> filterBySubject(String subjectName) {
        return studentSubjectRepository.searchBySubjectName(subjectName);
    }

}
