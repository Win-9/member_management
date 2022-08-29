package time.management.domain;



import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
@ToString(of = {"id", "name", "grade", "studentStatus"})

public class Member {
    @Id
    @Column(name = "STUDENT_ID")
    @NotNull
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "MAJOR_NAME")
    private Major major;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    private Position position;

    private int grade;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private StudentStatus studentStatus;
    
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private CountInfo countInfo;

    //Major add
    public void changeMajor(Major major){
        if (major != null) {
            this.major = major;
        }
    }

    public void changeMemberInfo(String id, String name, int grade){
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public void changeMemberInfo(String id, String name, int grade, Major major){
        this.id = id;
        this.name = name;
        this.grade = grade;
        changeMajor(major);
    }

    public void changeMemberInfo(String name, int grade, Major major){
        this.name = name;
        this.grade = grade;
        changeMajor(major);
    }

    public void changeMemberInfo(String name, int grade){
        this.name = name;
        this.grade = grade;
    }

    public void changeMemberInfoDetails(Position position, String phoneNumber, StudentStatus studentStatus, Gender gender, CountInfo countInfo){
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.studentStatus = studentStatus;
        this.gender = gender;
        this.countInfo = countInfo;
    }

    public void changeMemberInfoDetails(Position position, String phoneNumber, StudentStatus studentStatus, Gender gender){
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.studentStatus = studentStatus;
        this.gender = gender;
    }
}
