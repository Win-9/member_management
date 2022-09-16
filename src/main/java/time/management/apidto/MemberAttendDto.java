package time.management.apidto;


import lombok.Data;
import time.management.domain.CountInfo;
import time.management.domain.Member;

@Data
public class MemberAttendDto {

    private String id;
    private String name;
    private CountInfo attendInfo;

    public MemberAttendDto(Member member) {
        id = member.getId();
        name = member.getName();
        attendInfo = member.getCountInfo();
    }

}
