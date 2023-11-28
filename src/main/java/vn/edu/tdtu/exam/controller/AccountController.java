package vn.edu.tdtu.exam.controller;

import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.exam.dto.AccountDTO;
import vn.edu.tdtu.exam.dto.StudentDTO;
import vn.edu.tdtu.exam.entity.Account;
import vn.edu.tdtu.exam.entity.Admin;
import vn.edu.tdtu.exam.entity.Student;
import vn.edu.tdtu.exam.entity.Teacher;
import vn.edu.tdtu.exam.service.AccountService;
import vn.edu.tdtu.exam.service.StudentService;
import vn.edu.tdtu.exam.service.TeacherService;
import vn.edu.tdtu.exam.utils.SecurityUtil;

<<<<<<< HEAD
import java.util.Map;
import java.util.Optional;
=======
import java.util.List;
>>>>>>> e8315c946dda47f7bf4fac2c125f01b1cb127b35

@Controller
@RequestMapping("/")
public class AccountController {
    private final AccountService accountService;
    private final TeacherService teacherService;

    @Autowired
    public AccountController(AccountService accountService, TeacherService teacherService) {
        this.accountService = accountService;
        this.teacherService = teacherService;
    }

    @GetMapping()
    public String index(Model model, HttpSession session) {
        String role = (String) session.getAttribute("role");
        Long id = (Long) session.getAttribute("id");
        model.addAttribute("role", role);
        model.addAttribute("id", id);
        System.out.println("ROLE: " + role);
        if (role.equals("teacher")) {
            model = getTeacherInformation(model, id);
        }
        return "layouts/home";
    }

    private Model getTeacherInformation(Model model, Long id) {
        Teacher teacher = teacherService.getTeacher(id);
        List<String> educational = List.of(teacher.getEducationalBackground().split(";"));
        model.addAttribute("name", teacher.getName());
        model.addAttribute("email", teacher.getEmail());
        model.addAttribute("phone", teacher.getPhone());
        model.addAttribute("dob", teacher.getDoB());
        model.addAttribute("address", teacher.getAddress());
        model.addAttribute("faculty", teacher.getFaculty());
        model.addAttribute("workplace", teacher.getWorkplace());
        model.addAttribute("position", teacher.getPosition());
        model.addAttribute("degree", teacher.getDegree());
        model.addAttribute("eduBg", educational);
        model.addAttribute("research", teacher.getField());
        return model;
    }

    @GetMapping("/login")
    public String loginGetRequest() {
        return "login";
    }

    @GetMapping("/login/fail")
    public String loginFail(Model model) {
        model.addAttribute("failedMessage", "Invalid email or password");
        return "login";
    }

    @PostMapping(value = "/login", consumes = "application/x-www-form-urlencoded")
    public String loginPostRequest(@RequestParam String email, @RequestParam String password, HttpSession session) {
//        After login successfully, check role of user account
        String role = "teacher";
        Long id = 2L;
        session.setAttribute("role", role);
        session.setAttribute("id", id);
        session.setMaxInactiveInterval(3600); // 1 hour
        return "redirect:/";
    }
}





