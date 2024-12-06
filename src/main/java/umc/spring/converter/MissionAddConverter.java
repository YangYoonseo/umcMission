package umc.spring.converter;

import org.springframework.stereotype.Component;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@Component
public class MissionAddConverter {
    public Mission toEntity(MissionRequestDTO dto, Store store) {
        return Mission.builder()
                .reward(dto.getReward())
                .deadline(dto.getDeadline().toLocalDate())
                .missionSpec(dto.getMissionSpec())
                .store(store)
                .build();
    }

    public MissionResponseDTO toResponseDto(Mission mission) {
        return MissionResponseDTO.builder()
                .missionId(mission.getId())
                .build();
    }
}
