package vn.edu.tdtu.exam.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.tdtu.exam.entity.Account;
import vn.edu.tdtu.exam.entity.ExamPaper;
import vn.edu.tdtu.exam.entity.Subject;
import vn.edu.tdtu.exam.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ExamPaperService testService;

    @Autowired
    private ExamService examService;

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
                List<ExamPaper> tests = testService.getTestsBySubject(subject.getId());
                quantity.add(tests.size());
            });
            model.addAttribute("subjects", subjects);
            model.addAttribute("quantity", quantity);
            return "teacher/bank-exam";
        }
    }

    @GetMapping("/exam")
    public String showExam(@RequestParam(name = "s") Long subjectId, Model model) {
        if (subjectId != null) {
            List<ExamPaper> tests = testService.getTestsBySubject(subjectId);

            // set attribute
            List<String> teachers = new ArrayList<>();
            List<String> exams = new ArrayList<>();
            tests.forEach(test -> {
                teachers.add(
                        accountService.getTeacherNameById(test.getTeacher().getId())
                );
                exams.add(
                        examService.getExamById(test.getExam().getId()).getName()
                );
            });

            // return attributes to view
            model.addAttribute("isExam", true);
            model.addAttribute("quantity", tests.size());
            model.addAttribute("subject", subjectId);
            if (tests.size() == 0) {
                model.addAttribute("errMsg", "This subject does not have any exams");
                return "teacher/bank-exam";
            }
            model.addAttribute("tests", tests);
            model.addAttribute("teachers", teachers);
            model.addAttribute("exams", exams);
            return "teacher/bank-exam";
        }
        return "500";
    }
}





