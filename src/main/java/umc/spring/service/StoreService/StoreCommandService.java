package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.exception.RegionNotFoundException;
import umc.spring.converter.StoreConverter;
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
    private final StoreConverter storeConverter;

    @Transactional
    public StoreResponseDTO addStore(Long regionId, StoreRequestDTO request) {
        // 지역 확인
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RegionNotFoundException("존재하지 않는 지역 ID입니다: " + regionId));

        // StoreCreateRequest -> Store 엔티티 변환
        Store store = storeConverter.toEntity(request, region);

        // Store 엔티티 저장
        Store savedStore = storeRepository.save(store);

        // Store 엔티티 -> StoreCreateResponse 변환
        return storeConverter.toDto(savedStore);
    }
}
