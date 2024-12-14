package umc.spring.converter;

import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewConverter {
    // ReviewCreateRequest -> Review 엔티티 변환
    public Review toEntity(ReviewRequestDTO request, Store store, Member member) {
        return Review.builder()
                .body(request.getDetail())
                .score((float) request.getRank())
                .store(store)
                .member(member)
                .build();
    }

    // Review 엔티티 -> ReviewCreateResponse 변환
    public ReviewResponseDTO toDto(Review review) {
        return ReviewResponseDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .build();
    }

//    리뷰 불러오기 9주차

    public static ReviewResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review) {
        return ReviewResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<ReviewResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::reviewPreViewDTO).collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

}
