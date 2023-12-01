package vn.edu.tdtu.exam.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.exam.dto.StudentDTO;
import vn.edu.tdtu.exam.entity.*;
import vn.edu.tdtu.exam.repository.StudentRepository;
import vn.edu.tdtu.exam.service.*;

import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private ExamService examService;
    @Autowired
    private ExamPaperService examPaperService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ExamResultService examResultService;

    @GetMapping("/exam_list")
    public String getExamList(Model model) {
        HashMap<Exam, ExamPaper> exam_list = new HashMap<>();
        List<Exam> exams = examService.getAllExams();
        for(Exam exam: exams){
            ExamPaper examPaper = examPaperService.getTestByExamId(exam.getId());
            exam_list.put(exam, examPaper);
        }
        System.out.println(exam_list);
        model.addAttribute("exams", exam_list);
        return "student/exam_list";
    }
    @GetMapping("/exam")
    public String joinExam() {
        return "student/exam";
    }

    @GetMapping("/reports")
    public String reports(HttpSession session, Model model) {
        Long id = (Long) session.getAttribute("id");
        Student student = studentService.getStudentById(id);
        List<Report> reports = reportService.getAllStudentReport(student);
        model.addAttribute("reports", reports);
        return "student/report";
    }
    @GetMapping("/add_report/{id}")
    public String report(HttpSession session, Model model, @PathVariable Long id){
        ExamPaper examPaper = examPaperService.getTestsById(id);
        model.addAttribute("examPaperId", examPaper.getId());
        model.addAttribute("examTitle", examPaper.getTitle());
        model.addAttribute("subjectName", examPaper.getExam().getName());
        return "student/report_form";
    }

    @PostMapping("/add_report/{id}")
    public String createReport(@PathVariable Long id, String description, HttpSession session){
        Long studentId = (Long)session.getAttribute("id");
        ExamPaper examPaper = examPaperService.getTestsById(id);
        Student student = studentService.getStudentById(studentId);
        Report report = reportService.add(new Report(examPaper, student, description));
        return "redirect:/student/reports";
    }

    @GetMapping("/results")
    public String result(Model model, HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        Student student = studentService.getStudentById(id);

        HashMap<ExamResult, ExamPaper> results = new HashMap<>();
        List<ExamResult> examResults = examResultService.getAllStudentExamResult(student);
        for(ExamResult examResult : examResults){
            ExamPaper examPaper = examPaperService.getTestsById(examResult.getExamPaper().getId());
            results.put(examResult, examPaper);
        }
        model.addAttribute("results",results);
        return "student/result";
    }

    @GetMapping("/schedule")
    public String schedule() {
        return "student/schedule";
    }

    @GetMapping("/update_info")
    public String getInfo(){
        return "info_form";
    }
    @PostMapping("/update_info")
    public String updateInfo(HttpSession session, @ModelAttribute StudentDTO studentDTO){
        Long id = (Long)session.getAttribute("id");
        StudentDTO student = studentService.updateStudent(id, studentDTO);
        return "redirect:/";
    }
}
