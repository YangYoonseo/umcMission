package umc.spring.repository.ReviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

import java.util.List;

public interface ReviewRepositoryCustom {
    Review createReview(Long memberId, Long StoreId, String body, float score);

}
