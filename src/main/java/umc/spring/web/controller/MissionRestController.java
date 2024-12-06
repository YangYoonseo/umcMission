package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.validation.annotation.ValidChallenge;
import umc.spring.web.dto.MissionChallengeResponseDTO;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

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
