package time.management.dto;


import lombok.Data;

import javax.persistence.Entity;

@Data
public class OrderDto {
    private String sortBase;
    private String orderOption;
}
