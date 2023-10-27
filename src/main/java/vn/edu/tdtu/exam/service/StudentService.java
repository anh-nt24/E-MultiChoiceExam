package vn.edu.tdtu.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.exam.dto.StudentDTO;
import vn.edu.tdtu.exam.entity.Student;
import vn.edu.tdtu.exam.repository.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student add(StudentDTO studentDTO) {
        Student student = new Student();
        student.setStudentId(studentDTO.getStudentId());
        student.setStudentName(studentDTO.getStudentName());
        student.setEmail(studentDTO.getEmail());
        student.setGender(studentDTO.getGender());
        student.setPassword(studentDTO.getPassword());
        return studentRepository.save(student);
    }
}
