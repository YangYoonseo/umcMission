package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import umc.spring.validation.validator.CategoriesExistValidator;
import jakarta.validation.Payload;
import umc.spring.validation.validator.StoreIdExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StoreIdExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistStoreId {

    String message() default "존재하지 않는 가게 ID입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
