package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.exception.StoreNotFoundException;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewResponseDTO addReview(Long userId, Long storeId, ReviewRequestDTO request) {
        // 가게 존재 여부 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("해당 ID의 가게가 존재하지 않습니다."));

        // 리뷰 생성 및 저장
        Review review = Review.builder()
                .body(request.getDetail())
                .score((float) request.getRank())
                .store(store)
                .build();

        Review savedReview = reviewRepository.save(review);

        return ReviewResponseDTO.builder()
                .reviewId(savedReview.getId())
                .storeId(store.getId())
                .detail(savedReview.getBody())
                .rank(request.getRank())
                .build();
    }


}
