package vn.edu.tdtu.exam.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.tdtu.exam.entity.Account;
import vn.edu.tdtu.exam.entity.Teacher;
import vn.edu.tdtu.exam.service.AccountService;
import vn.edu.tdtu.exam.service.ClassService;
import vn.edu.tdtu.exam.service.StudentService;
import vn.edu.tdtu.exam.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/class")
public class ClassesController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClassService classService;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String showMyClasses(Model model, HttpSession session) {
        System.out.println(session.getAttribute("id"));
//        Account account = accountService.getAccount(session.getAttribute("id"));
//
//        List<Class> activeClasses = classService.getActiveClassesByTeacher(account);
//
//        model.addAttribute("activeClasses", activeClasses);
        return "teacher/my-classes";
    }
}






