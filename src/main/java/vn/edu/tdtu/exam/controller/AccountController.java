package vn.edu.tdtu.exam.controller;

import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
import java.util.Map;
import java.util.Optional;
import java.util.List;

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

    @PostMapping(value = "/login", consumes = "application/x-www-form-urlencoded")
    public String loginPostRequest(
            @RequestParam String email,
            @RequestParam String password,
            RedirectAttributes redirectAttributes,
            HttpSession session) {
        Account user = accountService.getUserByEmail(email);
        if (user == null) {
            redirectAttributes.addFlashAttribute("flashMessage", "User not existed");
            redirectAttributes.addFlashAttribute("flashType", "failed");
            return "redirect:/login";
        }
        if (!user.getPassword().equals(password)) {
            redirectAttributes.addFlashAttribute("flashMessage", "Incorrect password");
            redirectAttributes.addFlashAttribute("flashType", "failed");
            return "redirect:/login";
        }
        // login successfully
        String role = user.getRole();
        Long id = user.getId();
        session.setAttribute("role", role);
        session.setAttribute("id", id);
        session.setMaxInactiveInterval(3600); // 1 hour
        return "redirect:/";
    }

    @GetMapping("/password")
    public String getPassword() {
        return "password";
    }

    @PostMapping("/password")
    public String changePassword() {
        return "...";
    }
}





