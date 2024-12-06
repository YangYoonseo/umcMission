package umc.spring.service.MissionService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.exception.MissionAlreadyChallengingException;
import umc.spring.apiPayload.exception.StoreNotFoundException;
import umc.spring.converter.MissionAddConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.MissionChallengeResponseDTO;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;
    private final MissionConverter missionConverter;
    private final MissionAddConverter missionAddConverter;

    @Transactional
    public MissionChallengeResponseDTO challengeMission(Long userId, Long missionId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션이 존재하지 않습니다."));


        if (memberMissionRepository.existsByMemberAndMission(member, mission)) {
            throw new MissionAlreadyChallengingException("이미 도전 중인 미션입니다.");
        }


        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();

        memberMissionRepository.save(memberMission);

        return missionConverter.toDto(memberMission);
    }

    @Transactional
    public MissionResponseDTO addMission(Long storeId, MissionRequestDTO requestDto) {
        // Store 존재 여부 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("해당 Store를 찾을 수 없습니다."));

        // Mission 생성 및 저장
        Mission mission = missionAddConverter.toEntity(requestDto, store);
        Mission savedMission = missionRepository.save(mission);

        return missionAddConverter.toResponseDto(savedMission);
    }


}
