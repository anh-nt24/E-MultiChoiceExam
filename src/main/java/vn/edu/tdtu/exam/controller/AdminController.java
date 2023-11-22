package vn.edu.tdtu.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.tdtu.exam.service.AccountService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping()
    public String admin() {
        return "admin/home";
    }

    @GetMapping("/list-exam")
    public String examList() {
        return "admin/list-exam";
    }

    @GetMapping("/plans-exam")
    public String plansExam() {
        return "admin/plans-exam";
    }

    @GetMapping("/reset-password")
    public String resetPassword() {
        return "admin/reset-password";
    }

    @GetMapping("/user-management")
    public String userManagement() {
        return "admin/user-management";
    }
    @GetMapping("/register-account")
    public String addUser() {
        return "admin/register-account";
    }
}
