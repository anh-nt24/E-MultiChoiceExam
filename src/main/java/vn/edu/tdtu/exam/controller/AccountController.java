package vn.edu.tdtu.exam.controller;

import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.exam.dto.AccountDTO;
import vn.edu.tdtu.exam.dto.StudentDTO;
import vn.edu.tdtu.exam.entity.Account;
import vn.edu.tdtu.exam.entity.Admin;
import vn.edu.tdtu.exam.entity.Student;
import vn.edu.tdtu.exam.service.AccountService;
import vn.edu.tdtu.exam.service.StudentService;
import vn.edu.tdtu.exam.utils.SecurityUtil;

@Controller
@RequestMapping("/")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public String index(Model model, HttpSession session) {
        String role = (String) session.getAttribute("role");
        String name = (String) session.getAttribute("name");
        Long id = (Long) session.getAttribute("id");
        model.addAttribute("role", role);
        model.addAttribute("name", name);
        model.addAttribute("id", id);
        System.out.println("ROLE: " + role);
        return "layouts/home";
    }

    @GetMapping("/login")
    public String loginRequest() {
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
        Long id = 1L;
        String name = "Nguyen Thanh Phong";
        session.setAttribute("role", role);
        session.setAttribute("name", name);
        session.setAttribute("id", id);
        return "redirect:/";
    }

    @GetMapping("/student/exam")
    public String exam() {
        return "student/exam";
    }

<<<<<<< HEAD
    @GetMapping("/")
    public String admin() {
        return "/admin/home";
    }

    @GetMapping("/list-exam")
    public String examList(){
        return "admin/list-exam";
    }

    @GetMapping("/plans-exam")
    public String plansExam(){
        return "admin/plans-exam";
    }
    @GetMapping("/reset-password")
    public String resetPassword(){
        return "admin/reset-password";
    }

    @GetMapping("/user-management")
    public String userManagement(){
        return "admin/user-management";
    }
=======

>>>>>>> 9b1f15a1d14c4ab5e1bc8be24325d17ed3354855

}





