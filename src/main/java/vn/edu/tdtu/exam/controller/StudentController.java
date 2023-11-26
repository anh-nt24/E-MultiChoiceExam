package vn.edu.tdtu.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/home")
    public String home() {
        return "student/home";
    }
    @GetMapping("/exam")
    public String joinExam() {
        return "student/exam";
    }

    @GetMapping("/report")
    public String report() {
        return "student/report";
    }

    @GetMapping("/result")
    public String result() {
        return "student/result";
    }

    @GetMapping("/schedule")
    public String schedule() {
        return "student/schedule";
    }
}
