package vn.edu.tdtu.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.exam.dto.AccountDTO;
import vn.edu.tdtu.exam.entity.Account;
import vn.edu.tdtu.exam.entity.ExamPaper;
import vn.edu.tdtu.exam.entity.Subject;
import vn.edu.tdtu.exam.repository.AccountRepository;
import vn.edu.tdtu.exam.repository.ExamPaperRepository;

import java.util.List;

@Service
public class ExamPaperService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ExamPaperRepository testRepository;

    public List<ExamPaper> getTestsBySubject(Long subjectId) {
        return testRepository.findBySubjectId(subjectId);
    }
}
