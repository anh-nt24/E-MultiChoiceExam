package vn.edu.tdtu.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.exam.entity.ExamResult;
import vn.edu.tdtu.exam.entity.Student;
import vn.edu.tdtu.exam.repository.ExamResultRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExamResultService {
    @Autowired
    private ExamResultRepository examResultRepository;

    public ExamResult add(ExamResult examResult){
        return examResultRepository.save(examResult);
    }
    public List<ExamResult> getAllExamResult(){
        return examResultRepository.findAll();
    }

    public ExamResult getExamResultById(Long id){
        Optional<ExamResult> result = examResultRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }

    public List<ExamResult> getAllStudentExamResult(Student student){
        return examResultRepository.findAllExamResultByStudent(student);
    }

    public ExamResult updateExamResultById(Long id, ExamResult examResult){
        Optional<ExamResult> result = examResultRepository.findById(id);
        if(result.isPresent()){
            ExamResult e = result.get();
            e.setExamPaper(examResult.getExamPaper());
            e.setId(examResult.getId());
            e.setAttempt(examResult.getAttempt());
            e.setStudent(examResult.getStudent());
            e.setScore(examResult.getScore());
            e.setStartTime(e.getStartTime());
            e.setTakenTime(e.getTakenTime());

            return examResultRepository.save(e);
        }
        return null;
    }

    public boolean deleteExamResultById(Long id){
        try{
            examResultRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Double> getScoresTestId(Long testId) {
        return examResultRepository.findScoresByExamPaperId(testId);
    }
}
