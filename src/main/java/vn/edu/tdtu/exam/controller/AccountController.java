package vn.edu.tdtu.exam.controller;

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
    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/login/fail")
    public String loginFail(Model model) {
        model.addAttribute("failedMessage", "Invalid email or password");
        return "account/login";
    }

    @PostMapping(value = "/login", consumes = "application/x-www-form-urlencoded")
    public Account checkLogin(@RequestParam String email, @RequestParam String password) {
        System.out.println(email);
        System.out.println(password);
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        accountService.find(account);
        return null;
    }

    @GetMapping("/student/exam")
    public String exam() {
        return "student/exam";
    }

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

}





