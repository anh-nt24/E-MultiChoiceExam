package vn.edu.tdtu.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.exam.entity.Exam;
import vn.edu.tdtu.exam.entity.Option;
import vn.edu.tdtu.exam.repository.AccountRepository;
import vn.edu.tdtu.exam.repository.ExamRepository;
import vn.edu.tdtu.exam.repository.OptionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;

    public Option addOption(Option option) {
        return optionRepository.save(option);
    }
}
