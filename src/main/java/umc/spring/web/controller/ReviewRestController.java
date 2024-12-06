package umc.spring.web.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.spring.service.ReviewCommandService;
import umc.spring.validation.annotation.ExistStoreId;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewRestController {
    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{userId}/{storeId}")
    public ResponseEntity<ReviewResponseDTO> addReview(
            @PathVariable Long userId,
            @PathVariable @ExistStoreId Long storeId,
            @RequestBody @Valid ReviewRequestDTO request) {
        ReviewResponseDTO response = reviewCommandService.addReview(userId, storeId, request);
        return ResponseEntity.ok(response);
    }
}
