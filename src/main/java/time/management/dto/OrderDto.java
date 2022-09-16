package time.management.dto;


import lombok.Data;

import javax.persistence.Entity;

@Data
public class OrderDto {
    private String sortBase = "이름";
    private String orderOption = "오름차순";
}
