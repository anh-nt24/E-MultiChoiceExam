package vn.edu.tdtu.exam.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.tdtu.exam.entity.Account;
import vn.edu.tdtu.exam.repository.AccountRepository;
import vn.edu.tdtu.exam.service.AccountService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @RequestMapping(value = "/register-account", method = RequestMethod.POST)
    public String registerAccount(Model model, @RequestParam("image") MultipartFile image,
                                  @RequestParam("name") String name, @RequestParam("email") String email,
                                  @RequestParam("password") String password, @RequestParam("birth") LocalDate birth,
                                  @RequestParam("workplace") String workplace, @RequestParam("role") String role,
                                  @RequestParam("address") String address, @RequestParam("phone") String phone) throws IOException {
        try {
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());

            String uploadDir = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "uploads";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = image.getInputStream()) {
                String uniqueFileName = generateUniqueFileName(fileName);
                Path filePath = uploadPath.resolve(uniqueFileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) {
                throw new IOException("Could not save image file: " + fileName, ioe);
            }
            String imagePath = "/uploads/" + fileName;
            Account account = new Account(email, password, name, address, workplace, phone, birth, imagePath, true, role);
            accountService.save(account);
            return "redirect:/admin/user-management";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred during registration. Please try again.");
            return "error";
        }
    }

    private String generateUniqueFileName(String fileName) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        return timestamp + "_" + fileName;
    }
    @RequestMapping(value = "/update-user-status/{id}", method = RequestMethod.POST)
    public String update(Model model, @PathVariable Long id, @RequestParam("image") MultipartFile image,
                         @RequestParam("name") String name, @RequestParam("email") String email,
                         @RequestParam("password") String password, @RequestParam("birth") LocalDate birth,
                         @RequestParam("workplace") String workplace, @RequestParam("role") String role,
                         @RequestParam("address") String address, @RequestParam("phone") String phone,
                         @RequestParam("active") Boolean active) throws IOException {

        Optional<Account> optionalAccount = accountService.findById(id);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setEmail(email);
            account.setPassword(password);
            account.setAddress(address);
            account.setName(name);
            account.setRole(role);
            account.setPhone(phone);
            account.setWorkplace(workplace);
            account.setDoB(birth);
            account.setIsActive(active);

            try {
                String fileName = StringUtils.cleanPath(image.getOriginalFilename());

                String uploadDir = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "uploads";
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = image.getInputStream()) {
                    String uniqueFileName = generateUniqueFileName(fileName);
                    Path filePath = uploadPath.resolve(uniqueFileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ioe) {
                    throw new IOException("Could not save image file: " + fileName, ioe);
                }

                String imagePath = "/uploads/" + fileName;
                account.setAvatar(imagePath);
                accountService.save(account);

                return "redirect:/admin/user-management";
            } catch (Exception e) {
                model.addAttribute("error", "An error occurred during registration. Please try again.");
                return "error";
            }
        } else {
            return "/admin/error";
        }
    }

    @GetMapping("/update-user-status/{id}")
    public String updateAccount(Model model, HttpServletRequest request, @PathVariable Long id){
        Optional<Account> optionalAccount = accountService.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            model.addAttribute("account", account);
            return "admin/update-account";
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
