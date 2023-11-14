package vn.edu.tdtu.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String studentId;
    private String studentName;
    private String gender;
    private String email;
    private String password;
}
