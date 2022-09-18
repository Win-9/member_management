package time.management.dto;


import lombok.Data;

@Data
public class MemberOrderDto {
    private String sortBase = "이름";
    private String orderOption = "오름차순";
}
