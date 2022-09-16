package time.management.apidto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import time.management.domain.*;

import javax.persistence.*;

@Data
public class MemberListApi {

    private String id;

    private String major;

    private String name;

    private Position position;

    private int grade;

    private String phoneNumber;

    private StudentStatus studentStatus;

    private Gender gender;

    private CountInfo countInfo;

    public MemberListApi(Member member) {
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
