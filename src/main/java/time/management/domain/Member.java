package time.management.domain;



import lombok.Getter;
import javax.persistence.*;

@Entity
@Getter
public class Member {
    @Id
    @Column(name = "STUDENT_ID")
    private String id;

    @Transient
    private int index;

    @ManyToOne
    private Major major;

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
    public void addMajor(Member member){
        member.getMajor().getMembers().add(this);
    }

    public void createBasicMember(String id, String name, int grade, Major major){
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.major = major;
    }

    public void addDetails(Position position, String phoneNumber, StudentStatus studentStatus, Gender gender, CountInfo countInfo){
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.studentStatus = studentStatus;
        this.gender = gender;
        this.countInfo = countInfo;
    }

    public void setIndex(int index){
        this.index = index;
    }
}
