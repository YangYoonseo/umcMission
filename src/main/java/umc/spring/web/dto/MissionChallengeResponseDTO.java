package umc.spring.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MissionChallengeResponseDTO {
    private Long memberId;
    private Long missionId;
    private String status;
}
