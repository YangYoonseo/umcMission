package umc.spring.web.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/{regionId}")
    public ResponseEntity<StoreResponseDTO> addStore(
            @PathVariable Long regionId,
            @RequestBody @Valid StoreRequestDTO request) {
        StoreResponseDTO response = storeCommandService.addStore(regionId, request);
        return ResponseEntity.ok(response);
    }


}
