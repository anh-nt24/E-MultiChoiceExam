package vn.edu.tdtu.exam.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.tdtu.exam.dto.AccountDTO;
import vn.edu.tdtu.exam.dto.ExamPaperDTO;
import vn.edu.tdtu.exam.entity.*;
import vn.edu.tdtu.exam.repository.AccountRepository;
import vn.edu.tdtu.exam.repository.ExamPaperRepository;
import vn.edu.tdtu.exam.repository.ExamRepository;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExamPaperService {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int TOKEN_LENGTH = 6;

    @Autowired
    private ExamPaperRepository testRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ExamService examService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private OptionService optionService;

    public List<ExamPaper> getTestsBySubject(Long subjectId) {
        return testRepository.findBySubjectId(subjectId);
    }

    public Boolean addTest(Long teacherId, ExamPaperDTO form, MultipartFile file) {
        // read data from the submitted form
        ExamPaper examPaper = convertDTOtoEntity(form);
        examPaper.setTeacher(accountService.getTeacher(teacherId));
        examPaper.setDateCreated(LocalDateTime.now());
        examPaper.setLastModified(LocalDateTime.now());
        examPaper.setAccessToken(generateRandomToken());
        ExamPaper savedPaper = testRepository.save(examPaper);
        Boolean result = readFile(file, savedPaper);
        return true;
    }

    private ExamPaper convertDTOtoEntity(ExamPaperDTO form) {
        ExamPaper examPaper = new ExamPaper();
        examPaper.setTitle(form.getTitle());
        examPaper.setSubject(subjectService.getSubjectById(form.getSubject()));
        examPaper.setExam(examService.getExamById(form.getCategory()));
        examPaper.setDuration(form.getDuration());
        examPaper.setTimesAllowed(form.getTimesAllowed());
        examPaper.setShowScore(form.getShowScore());
        return examPaper;
    }

    private Boolean readFile(MultipartFile file, ExamPaper examPaper) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<String[]> csvData = reader.readAll();
            for (String[] row : csvData) {
                Question question = new Question();
                question.setContent(row[0]);
                question.setExamPaper(examPaper);
                Question savedQuestion = questionService.addQuestion(question);

                int correct = Integer.parseInt(row[5]);
                for(int i=1; i<=4; ++i) {
                    Boolean isCorrect = (correct == i);
                    Option option = new Option();
                    option.setContent(row[i]);
                    option.setIsCorrect(isCorrect);
                    option.setQuestion(savedQuestion);
                    optionService.addOption(option);
                }
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String generateRandomToken() {
        SecureRandom random = new SecureRandom();
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < TOKEN_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            token.append(randomChar);
        }

        return token.toString();
    }
}
