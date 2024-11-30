package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.exception.RegionNotFoundException;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

@Service
@RequiredArgsConstructor
public class StoreCommandService {
    private final RegionRepository regionRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public StoreResponseDTO addStore(Long regionId, StoreRequestDTO request) {
        // 지역 확인
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RegionNotFoundException("존재하지 않는 지역 ID입니다: " + regionId));

        // 가게 생성
        Store store = Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(region)
                .build();
        Store savedStore = storeRepository.save(store);

        // 응답 생성
        return StoreResponseDTO.builder()
                .storeId(savedStore.getId())
                .name(savedStore.getName())
                .build();
    }
}
