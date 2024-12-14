package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.validation.annotation.ValidChallenge;
import umc.spring.web.dto.MissionChallengeResponseDTO;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/{userId}/{missionId}")
    public ResponseEntity<MissionChallengeResponseDTO> challengeMission(
            @PathVariable Long userId,
            @PathVariable @ValidChallenge Long missionId) {
        MissionChallengeResponseDTO response = missionCommandService.challengeMission(userId, missionId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{storeId}")
    public ResponseEntity<MissionResponseDTO> addMission(
            @PathVariable Long storeId,
            @Valid @RequestBody MissionRequestDTO requestDto) {

        MissionResponseDTO response = missionCommandService.addMission(storeId, requestDto);
        return ResponseEntity.ok(response);
    }



}
