package umc.spring.web.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ReviewRequestDTO {
    private int rank;
    private String detail;
    private List<String> imageUrl;

}
