package vn.edu.tdtu.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.exam.entity.Student;
import vn.edu.tdtu.exam.entity.StudentSubject;
import vn.edu.tdtu.exam.entity.Subject;
import vn.edu.tdtu.exam.repository.StudentSubjectRepository;

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
    public List<StudentSubject> getStudentSubjectByStudent(Student student){
        return studentSubjectRepository.findAllStudentSubjectByStudent(student);
    }

    public StudentSubject getStudentSubjectByStudentAndSubject(Student student, Subject subject){
        return studentSubjectRepository.findByStudentAndSubject(student, subject);
//        if(result.isPresent()){
//            return result.get();
//        }
//        return null;
    }

}
