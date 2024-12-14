package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.validation.annotation.ExistCategories;
import umc.spring.validation.annotation.ExistStoreId;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StoreIdExistValidator implements ConstraintValidator<ExistStoreId, Long> {
    private final StoreRepository storeRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // null 값은 유효하지 않음
        }

        boolean exists = storeRepository.existsById(value);
        if (!exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("존재하지 않는 가게 ID입니다.")
                    .addConstraintViolation();
        }

        return exists;
    }

}
