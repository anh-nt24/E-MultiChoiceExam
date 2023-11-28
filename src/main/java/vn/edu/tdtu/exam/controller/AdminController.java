package vn.edu.tdtu.exam.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.exam.entity.Account;
import vn.edu.tdtu.exam.repository.AccountRepository;
import vn.edu.tdtu.exam.service.AccountService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired AccountService accountService;
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
    public String userManagement(Model model) {
        List<Account> userList = accountRepository.findAll();
        model.addAttribute("userList", userList);
        return "admin/user-management";
    }
    @GetMapping("/register-account")
    public String addUser() {
        return "admin/register-account";
    }
    @PostMapping("/update-user-status/{id}")
    public String updateUserStatus(@PathVariable Long id, @RequestParam Boolean active) {
        Optional<Account> optionalAccount = accountService.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setIsActive(active);
            accountService.save(account);
            return "redirect:/admin/user-management";
        } else {
            return "admin/error";
        }
    }
    @GetMapping("/update-user-status/{id}")
    public String updateAccount(Model model, HttpServletRequest request, @PathVariable Long id){
        Optional<Account> optionalAccount = accountService.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            model.addAttribute("account", account);
            return "admin/updateAccount";
        }
        else {
            return "admin/error";
        }
    }
    @GetMapping("/byRole")
    public ResponseEntity<List<Account>> getUsersByRole(@RequestParam String role) {
        List<Account> users = accountService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }
}
