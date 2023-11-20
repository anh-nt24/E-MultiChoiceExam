package vn.edu.tdtu.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.exam.entity.Exam;
import vn.edu.tdtu.exam.entity.ExamPaper;
import vn.edu.tdtu.exam.repository.AccountRepository;
import vn.edu.tdtu.exam.repository.ExamPaperRepository;
import vn.edu.tdtu.exam.repository.ExamRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ExamRepository examRepository;

    public String getExamById(Long id) {
        Optional<Exam> optionalExam = examRepository.findById(id);
        if (optionalExam.isPresent()) {
            return optionalExam.get().getName();
        }
        return null;
    }
}
