package umc.spring.converter;

import org.springframework.stereotype.Component;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

@Component
public class StoreConverter {
    // StoreCreateRequest -> Store 엔티티 변환
    public Store toEntity(StoreRequestDTO request, Region region) {
        return Store.builder()
                .region(region)
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }

    // Store 엔티티 -> StoreCreateResponse 변환
    public StoreResponseDTO toDto(Store store) {
        return StoreResponseDTO.builder()
                .storeId(store.getId())
                .name(store.getName())
                .build();
    }
}
