package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.exception.MemberNotFoundException;
import umc.spring.apiPayload.exception.StoreNotFoundException;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewConverter reviewConverter;
    private final MemberRepository memberRepository;

    @Transactional
    public ReviewResponseDTO addReview(Long userId, Long storeId, ReviewRequestDTO request) {
        // 가게 존재 여부 확인
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreNotFoundException("해당 ID의 가게가 존재하지 않습니다."));

        Member member = memberRepository.findById(userId).orElseThrow(() -> new MemberNotFoundException("해당 member는 존재하지 않습니다"));

        // ReviewRequestDTO -> Review 엔티티 변환
        Review review = reviewConverter.toEntity(request,store,member);

        // 리뷰 저장
        Review savedReview = reviewRepository.save(review);

        // Review 엔티티 -> ReviewResponseDTO 변환
        return reviewConverter.toDto(savedReview);
    }


}
