package time.management.dto;

import lombok.Data;
import time.management.domain.Position;

@Data
public class MemberSearchDto {
    private Integer grade;
    private String studentID;
    private String major;
    private String name;
    private Position position;
}
