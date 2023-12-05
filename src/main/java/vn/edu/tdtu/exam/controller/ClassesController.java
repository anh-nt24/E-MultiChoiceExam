package vn.edu.tdtu.exam.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.tdtu.exam.entity.Account;
import vn.edu.tdtu.exam.entity.Subject;
import vn.edu.tdtu.exam.entity.Teacher;
import vn.edu.tdtu.exam.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/class")
public class ClassesController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentService studentService;


    @GetMapping
    public String showMyClasses(Model model, HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!role.equals("teacher")) {
            return "404";
        }

        List<Subject> subjects = subjectService.getSubjectOfTeacher((Long) session.getAttribute("id"));
        List<String> schedules = new ArrayList<>();
        List<Integer> students = new ArrayList<>();

        subjects.forEach(subject -> {
            String schedule = subjectService.getScheduleOfTeacher(subject.getId());
            Integer student = subjectService.findStudentInSubject((subject.getId()));

            schedules.add(schedule);
            students.add(student);

        });

        model.addAttribute("listClass", subjects);
        model.addAttribute("schedule", schedules);
        model.addAttribute("numberOfStudent", students);

        return "teacher/my-classes";
    }
}






