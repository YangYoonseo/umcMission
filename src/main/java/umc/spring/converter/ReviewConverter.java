package umc.spring.converter;

import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@Component
public class ReviewConverter {
    // ReviewCreateRequest -> Review 엔티티 변환
    public Review toEntity(ReviewRequestDTO request, Store store) {
        return Review.builder()
                .body(request.getDetail())
                .score((float) request.getRank())
                .store(store)
                .build();
    }

    // Review 엔티티 -> ReviewCreateResponse 변환
    public ReviewResponseDTO toDto(Review review) {
        return ReviewResponseDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .build();
    }
}
