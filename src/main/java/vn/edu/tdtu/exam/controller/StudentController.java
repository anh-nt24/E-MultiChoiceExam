package vn.edu.tdtu.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.exam.dto.StudentDTO;
import vn.edu.tdtu.exam.entity.Student;
import vn.edu.tdtu.exam.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public Student createStudent(@RequestBody StudentDTO userDTO) {
        return studentService.add(userDTO);
    }
}





