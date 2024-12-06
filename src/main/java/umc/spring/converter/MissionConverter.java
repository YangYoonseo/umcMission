package umc.spring.converter;

import org.springframework.stereotype.Component;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionChallengeResponseDTO;

@Component
public class MissionConverter {

    // MemberMission 엔티티 -> MissionResponseDTO 변환
    public MissionChallengeResponseDTO toDto(MemberMission memberMission) {
        return MissionChallengeResponseDTO.builder()
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .status(memberMission.getStatus().name())
                .build();
    }
}
