package time.management.dto;

import lombok.Data;

@Data
public class MemberAttendListOrderDto {
    private String sortBase = "Attend";
    private String orderOption = "내림차순";
}
