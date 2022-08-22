package time.management.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CountInfo {
    @Column(name = "ATTENDANCE_COUNT")
    private int attendanceCount;

    @Column(name = "QUIZ_COUNT")
    private int quizCount;

    @Column(name = "QUESTION_COUNT")
    private int questionCount;

}
