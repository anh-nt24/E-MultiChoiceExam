package vn.edu.tdtu.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.exam.entity.Exam;
import vn.edu.tdtu.exam.repository.AccountRepository;
import vn.edu.tdtu.exam.repository.ExamRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ExamRepository examRepository;

    public Exam getExamById(Long id) {
        Optional<Exam> optionalExam = examRepository.findById(id);
        if (optionalExam.isPresent()) {
            return optionalExam.get();
        }
        return null;
    }

    public List<Exam> getAllExams() {
        Sort sortByDateDesc = Sort.by(Sort.Direction.DESC, "examDate");
        return examRepository.findAll(sortByDateDesc);
    }
}
