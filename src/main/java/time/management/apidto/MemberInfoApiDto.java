package time.management.apidto;


import lombok.Data;
import time.management.domain.*;

@Data
public class MemberInfoApiDto {

    private String id;

    private String major;

    private String name;

    private Position position;

    private int grade;

    private String phoneNumber;

    private StudentStatus studentStatus;

    private Gender gender;

    private CountInfo countInfo;

    public MemberInfoApiDto(Member member) {
        id = member.getId();
        major = member.getMajor().getName();
        name = member.getName();
        position = member.getPosition();
        grade = member.getGrade();
        phoneNumber = member.getPhoneNumber();
        studentStatus = member.getStudentStatus();
        gender = member.getGender();
        countInfo = member.getCountInfo();
    }

}
