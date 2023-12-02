package vn.edu.tdtu.exam.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.exam.entity.*;
import vn.edu.tdtu.exam.service.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ExamService examService;
    @Autowired
    private ExamPaperService examPaperService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private OptionService optionService;
    @Autowired
    private StudentSubjectService studentSubjectService;
    @GetMapping()
    public String joinExam(HttpSession session, Model model){
        Long examPaperId = (Long)session.getAttribute("examPaperId");
        if(examPaperId == null){
            return "error";
        }

        ExamPaper examPaper = examPaperService.getTestsById(examPaperId);
        session.getAttribute("examPaperId");

        HashMap<Question, List<Option>> questionList = new HashMap<>();
        List<Question> questions = questionService.getQuestionByExamPaper(examPaper);

        for(Question q: questions){
            questionList.put(q, optionService.getOptionByQuestion(q));
        }
        model.addAttribute("questions", questionList);
        model.addAttribute("duration", examPaper.getDuration());
        model.addAttribute("exam", examPaperId);
        return "student/exam";
    }
    @GetMapping("/exam_enroll/{id}")
    public String tokenEnroll(@PathVariable Long id, HttpSession session, Model model){
        session.removeAttribute("examPaperId");
        Long studentId = (Long)session.getAttribute("id");

        Student student = studentService.getStudentById(studentId);
        ExamPaper examPaper = examPaperService.getTestsById(id);

        StudentSubject studentSubject = studentSubjectService.getStudentSubjectByStudentAndSubject(student, examPaper.getSubject());

        System.out.println(student);
        System.out.println(examPaper);
        System.out.println(studentSubject);

        if(studentSubject != null && !studentSubject.getBanned() && examPaper.getIsActive()) {
            model.addAttribute("examPaperId", id);
            model.addAttribute("examTitle", examPaper.getExam().getName());
            model.addAttribute("subjectName", examPaper.getSubject().getName());
            return "student/exam_enroll";
        }
        return "error";
    }
    @PostMapping("/exam_enroll/{id}")
    public String joinExam(@PathVariable Long id, String token,  HttpSession session){
        ExamPaper examPaper = examPaperService.getTestsById(id);
        Long studentId = (Long)session.getAttribute("id");

        if(token.equals(examPaper.getAccessToken())){
            session.setAttribute("examPaperId", examPaper.getId());
            return "redirect:/exam";
        }
        return "redirect:/exam_enroll/"+id;
    }

    @PostMapping(value = "exam/submit/{id}", consumes = "application/x-www-form-urlencoded")
    public void calculateScore(@PathVariable Long examId, @RequestBody MultiValueMap<String, List<String>> formData, @RequestParam String body){
        System.out.println("YAS");
        System.out.println(formData);
        System.out.println(formData);
        System.out.println(body);
    }
}
