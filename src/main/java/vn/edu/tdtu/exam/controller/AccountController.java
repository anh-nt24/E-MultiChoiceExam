package vn.edu.tdtu.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.tdtu.exam.dto.AccountDTO;
import vn.edu.tdtu.exam.dto.StudentDTO;
import vn.edu.tdtu.exam.entity.Account;
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

    @PostMapping("/login")
    public Account checkLogin(@RequestBody AccountDTO accountDTO) {
        String email = accountDTO.getEmail();
        String password = accountDTO.getPassword();
        Account account = new Account(email, password);
        accountService.find(account);
        return null;
    }
}





