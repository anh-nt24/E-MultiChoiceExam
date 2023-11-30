package vn.edu.tdtu.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.tdtu.exam.entity.ResetPassword;
import vn.edu.tdtu.exam.service.ResetPasswordService;

import java.security.SecureRandom;
import java.util.List;

@Controller
public class ResetPasswordController {

    private final JavaMailSender javaMailSender;
    @Autowired
    ResetPasswordService resetPasswordService;

    public ResetPasswordController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @GetMapping("/admin/reset-password")
    public String resetPassword(Model model) {
        List<ResetPassword> resetPasswords = resetPasswordService.findByStatus("not");
        model.addAttribute("reset", resetPasswords);
        return "admin/reset-password";
    }

    @PostMapping("/admin/reset-password/send")
    public String sendResetPassword(@RequestParam("email") String email) {
        String newPassword = generateRandomPassword();

        sendEmail(email, newPassword);

        resetPasswordService.updatePasswordByEmail(email, newPassword);

        return "redirect:/admin/reset-password";
    }
    private static final String ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static String generateRandomPassword() {
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 9; i++) {
            int index = random.nextInt(ALL_CHARS.length());
            password.append(ALL_CHARS.charAt(index));
        }

        return password.toString();
    }

    private void sendEmail(String to, String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Password Reset");
        message.setText("Your new password is: " + newPassword);

        javaMailSender.send(message);
    }
}
