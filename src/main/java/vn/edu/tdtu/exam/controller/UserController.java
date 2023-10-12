package vn.edu.tdtu.exam.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.tdtu.exam.repository.UserRepository;

@Controller
public class UserController {
        @Autowired
        private UserRepository userRepository;

        @PostConstruct
        public void initData() {
            System.out.println("__________Reset and init data________________");
        }

        @RequestMapping("/")
        public String listUser(Model model) {
            model.addAttribute("users", userRepository.findAll());
            return "index";
        }
}
