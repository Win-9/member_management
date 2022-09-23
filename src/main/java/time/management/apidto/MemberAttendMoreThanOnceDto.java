package time.management.apidto;


import lombok.Data;
import time.management.domain.CountInfo;
import time.management.domain.Member;

@Data
public class MemberAttendMoreThanOnceDto {
    private String id;
    private String name;
    private String major;
    private int attendCount;

    public MemberAttendMoreThanOnceDto(Member member) {
        id = member.getId();
        name = member.getName();
        major = member.getMajor().getName();
        attendCount = member.getCountInfo().getAttendanceCount();
    }
}
