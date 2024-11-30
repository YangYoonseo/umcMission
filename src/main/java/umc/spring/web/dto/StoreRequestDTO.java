package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class StoreRequestDTO {
    private String name;
    private String address;
}
