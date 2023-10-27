package vn.edu.tdtu.exam.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private String studentId;
    private String studentName;
    private String gender;
    private String email;
    private String password;
}
