package umc.spring.web.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MissionRequestDTO {
    private Integer reward;
    private LocalDateTime deadline;
    private String missionSpec;

}
