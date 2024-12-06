package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.validation.annotation.ExistStoreId;
import umc.spring.validation.annotation.ValidChallenge;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MissionsChallengeValidator implements ConstraintValidator<ValidChallenge, Long> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(ValidChallenge constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean exists = memberMissionRepository.existsById(value);

        if (!exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_CHALLENGED.toString())
                    .addConstraintViolation();
        }

        return exists;
    }

}


