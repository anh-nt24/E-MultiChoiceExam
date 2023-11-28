package vn.edu.tdtu.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.exam.entity.Exam;
import vn.edu.tdtu.exam.entity.Question;
import vn.edu.tdtu.exam.repository.AccountRepository;
import vn.edu.tdtu.exam.repository.ExamRepository;
import vn.edu.tdtu.exam.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;


    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }
}