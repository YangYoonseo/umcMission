package umc.spring.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewResponseDTO {
    private Long reviewId;
    private Long storeId;
    private String detail;
    private int rank;
}
