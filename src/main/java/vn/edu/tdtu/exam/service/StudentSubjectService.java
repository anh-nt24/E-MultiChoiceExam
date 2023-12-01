package vn.edu.tdtu.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.exam.entity.Student;
import vn.edu.tdtu.exam.entity.StudentSubject;
import vn.edu.tdtu.exam.repository.StudentRepository;
import vn.edu.tdtu.exam.repository.StudentSubjectRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public StudentSubject getStudentSubjectByStudent(Student student){
        Optional<StudentSubject> result = studentSubjectRepository.findStudentSubjectByStudent(student);
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }

}
