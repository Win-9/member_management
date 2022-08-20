package time.management.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CountInfo {
    @Column(name = "ATTENDANCE_COUNT")
    private int attendanceCount;

    @Column(name = "QUIZ_COUNT")
    private int quizCount;

    @Column(name = "QUESTION_COUNT")
    private int questionCount;

}
