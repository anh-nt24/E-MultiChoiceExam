package vn.edu.tdtu.exam.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.tdtu.exam.dto.ExamPaperDTO;
import vn.edu.tdtu.exam.entity.Exam;
import vn.edu.tdtu.exam.entity.ExamPaper;
import vn.edu.tdtu.exam.entity.Subject;
import vn.edu.tdtu.exam.service.AccountService;
import vn.edu.tdtu.exam.service.ExamPaperService;
import vn.edu.tdtu.exam.service.ExamService;
import vn.edu.tdtu.exam.service.SubjectService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/paper")
public class ExamPaperController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ExamPaperService testService;

    @Autowired
    private ExamService examService;

    @GetMapping("/detail")
    public String showPaperDetail(@RequestParam Long id, Model model) {
        model.addAttribute("paperId", id);
        return "teacher/paper-detail";
    }

    @GetMapping("/add/{subjectId}")
    public String addNewPaper(@PathVariable Long subjectId, Model model) {
        Subject subject = subjectService.getSubjectById(subjectId);
        List<Exam> exams = examService.getAllExams();

        model.addAttribute("subject", subject);
        model.addAttribute("category", exams);
        return "teacher/exam-paper-form";
    }

    @PostMapping("/add")
    public String submitNewPaper(
            @ModelAttribute ExamPaperDTO form,
            @RequestParam("exampaper") MultipartFile file,
            HttpSession session,
            Model model) {

        Long teacherId = (Long) session.getAttribute("id");
        if (teacherId == null) {
            return "redirect:/login";
        }
        testService.addTest(teacherId, form, file);

        // handle file upload
        return "redirect:/bank/exam?s=" + form.getSubject();
    }
}






