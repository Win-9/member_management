package time.management.domain;



import lombok.Getter;
import javax.persistence.*;

@Entity
@Getter
public class Member {
    @Id
    @Column(name = "STUDENT_ID")
    private String id;

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
}
