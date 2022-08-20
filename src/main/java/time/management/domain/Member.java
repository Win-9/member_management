package time.management.domain;


import javax.persistence.*;

@Entity
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
}
