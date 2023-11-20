package vn.edu.tdtu.exam.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return "paper/detail";
    }

    @GetMapping("/add")
    public String addNewPaper() {
        return "";
    }
}






