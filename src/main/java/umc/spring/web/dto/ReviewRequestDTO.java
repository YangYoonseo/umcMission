package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ReviewRequestDTO {
    private int rank;
    private String detail;
    private List<String> imageUrl;


}
