package time.management.dto;


import lombok.Data;
import time.management.domain.Gender;
import time.management.domain.Position;
import time.management.domain.StudentStatus;

@Data
public class MemberFormDto {
    private String studentID;
    private String major;
    private String name;
    private int grade;
    private String phoneNumber;
    private Gender gender;
    private StudentStatus studentState;
    private Position position;
}