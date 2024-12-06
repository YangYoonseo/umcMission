package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.validation.annotation.ValidChallenge;
import umc.spring.web.dto.MissionChallengeResponseDTO;

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


}
