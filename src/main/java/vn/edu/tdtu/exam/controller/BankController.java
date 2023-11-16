package vn.edu.tdtu.exam.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.tdtu.exam.entity.Account;
import vn.edu.tdtu.exam.entity.Subject;
import vn.edu.tdtu.exam.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String showMySubject(Model model, HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!role.equals("teacher")) {
            return "404";
        }
        List<Subject> subjects = subjectService.getSubjectOfTeacher((Long) session.getAttribute("id"));
        if (subjects == null) {
            model.addAttribute("errMsg", "No subjects found");
            return "teacher/bank-exam";
        } else {
            List<Integer> quantity = new ArrayList<>();
            subjects.forEach(subject -> {
                quantity.add(
                        subjectService.getExamPaperOfSubjectQuantity(subject.getId())
                );
            });
            model.addAttribute("subjects", subjects);
            model.addAttribute("quantity", quantity);
            return "teacher/bank-exam";
        }
    }

    @GetMapping("/exam")
    public String showSubjectExam(@RequestParam(name = "s") Long subjectId, Model model) {
        if (subjectId != null) {
            Subject subject = subjectService.getSubjectById(subjectId);

            if (subject == null) {
                model.addAttribute("errMsg", "This subject does not have any exams");
                return "teacher/bank-exam";
            }
            model.addAttribute("subject", subject);
            return "teacher/bank-exam";
        }
        return "500";
    }
}






