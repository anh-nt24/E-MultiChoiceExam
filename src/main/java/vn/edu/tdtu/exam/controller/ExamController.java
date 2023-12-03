package vn.edu.tdtu.exam.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.tdtu.exam.entity.*;
import vn.edu.tdtu.exam.service.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    private ExamResultService examResultService;
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
    public String joinExam(HttpSession session, Model model, RedirectAttributes redirectAttributes){
        Long examPaperId = (Long)session.getAttribute("examPaperId");
        Long studentId = (Long)session.getAttribute("id");

        if(examPaperId == null){
            redirectAttributes.addFlashAttribute("flashMessage", "Please join the exam from Exam List");
            redirectAttributes.addFlashAttribute("flashType", "failed");
            return "redirect:/student/exam_list";
        }

        ExamPaper examPaper = examPaperService.getTestsById(examPaperId);
        Student student = studentService.getStudentById(studentId);

        //Check number of attempt
        List<ExamResult> check = examResultService.getExamResultByExamPaperAndStudent(examPaper, student);

        if(check.size()+1 > examPaper.getTimesAllowed()){
            redirectAttributes.addFlashAttribute("flashMessage", "You have reached maximum attempt number of the exam");
            redirectAttributes.addFlashAttribute("flashType", "failed");
            return "redirect:/student/exam_list";
        }
        //-----------------------------------------------------

        //QuestionId and Answers get for render
        TreeMap<Long, List<Option>> questionList = new TreeMap<>();
        List<Question> questions = questionService.getQuestionByExamPaper(examPaper);

        for(Question q: questions){
            questionList.put(q.getId(), optionService.getOptionByQuestion(q));
        }

        model.addAttribute("questions", questionList);
        model.addAttribute("duration", examPaper.getDuration());
        model.addAttribute("exam", examPaperId);

        session.setAttribute("startTime", LocalDateTime.now());

        return "student/exam";
    }
    @GetMapping("/exam_enroll/{id}")
    public String tokenEnroll(@PathVariable Long id, HttpSession session, Model model,
                              RedirectAttributes redirectAttributes){
        session.removeAttribute("examPaperId"); //remove if existed
        Long studentId = (Long)session.getAttribute("id");

        Student student = studentService.getStudentById(studentId);
        ExamPaper examPaper = examPaperService.getTestsById(id);

        //Check if student is banned from class subject
        StudentSubject studentSubject = studentSubjectService.getStudentSubjectByStudentAndSubject(student, examPaper.getSubject());

        if(studentSubject != null && !studentSubject.getBanned() && examPaper.getIsActive()) {
            model.addAttribute("examPaperId", id);
            model.addAttribute("examTitle", examPaper.getExam().getName());
            model.addAttribute("subjectName", examPaper.getSubject().getName());
            return "student/exam_enroll";
        }
        redirectAttributes.addFlashAttribute("flashMessage", "You are banned from enrolling the test");
        redirectAttributes.addFlashAttribute("flashType", "failed");
        //-----------------------------------------------------
        return "redirect:/student/exam_list";
    }

    @PostMapping("/exam_enroll/{id}")
    public String joinExam(@PathVariable Long id, String token,  HttpSession session, RedirectAttributes redirectAttributes){
        ExamPaper examPaper = examPaperService.getTestsById(id);

        Long studentId = (Long)session.getAttribute("id");

        //Check Access Token
        if(token.equals(examPaper.getAccessToken())){
            //Set examPaper to the session
            //To store examPaper id for exam rendering
            session.setAttribute("examPaperId", examPaper.getId());
            return "redirect:/exam";
        }
        //---------------------

        redirectAttributes.addFlashAttribute("flashMessage", "Wrong Token");
        redirectAttributes.addFlashAttribute("flashType", "failed");
        return "redirect:/exam/exam_enroll/"+id;
    }

    @PostMapping(value = "/submit/{id}", consumes = "application/x-www-form-urlencoded")
    public String submitExam(@PathVariable Long id, @RequestBody MultiValueMap<String, String> formData,
                             HttpSession session, RedirectAttributes redirectAttributes){

        Long examPaperId = (Long)session.getAttribute("examPaperId");
        Long studentId = (Long)session.getAttribute("id");

        ExamPaper examPaper = examPaperService.getTestsById(examPaperId);
        Student student = studentService.getStudentById(studentId);

        List<Question> questions = questionService.getQuestionByExamPaper(examPaper);
        Double scorePerQuestion = questions.size()/10.0;

            //formData: {questionId= [AnswerId]}
            //formData: {1=[2], 2=[7], 4=[14], 5=[18], 6=[23]}

        int numberOfCorrectAnswer = 0;
        Set<String> keySet = formData.keySet();
        for(String questionId : keySet){
            Question question = questionService.getQuestionById(Long.valueOf(questionId));

            if(optionService.checkTrueOptionByQuestion(question, Long.valueOf(formData.get(questionId).get(0))))
                numberOfCorrectAnswer++;
        }
        System.out.println(numberOfCorrectAnswer);

        //Check number of attempt
        List<ExamResult> check = examResultService.getExamResultByExamPaperAndStudent(examPaper, student);

        if(check.size()+1 > examPaper.getTimesAllowed()){
            redirectAttributes.addFlashAttribute("flashMessage", "You have already submitted the test");
            redirectAttributes.addFlashAttribute("flashType", "failed");
            return "redirect:/student/results";
        }

        //save Exam_Result
            //Score calculate
        double score = numberOfCorrectAnswer*scorePerQuestion*1.0;

            //Time taken calculate
        LocalDateTime timeStarted = (LocalDateTime) session.getAttribute("startTime");
        LocalDateTime timeEnded = LocalDateTime.now();
        Date start = new Date(0,0,0,timeStarted.getHour(), timeStarted.getMinute(), timeStarted.getSecond());
        Date end = new Date(0,0,0,timeEnded.getHour(), timeEnded.getMinute(), timeEnded.getSecond());

        long timeTaken = TimeUnit.MILLISECONDS.toMinutes(end.getTime() - start.getTime());

        ExamResult examResult = new ExamResult(student, examPaper,score, timeStarted, (int)timeTaken, check.size()+1);
        examResultService.add(examResult);

        session.removeAttribute("startTime");

        return "redirect:/student/results";
    }
}
