package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.CategoriesExistValidator;

import java.lang.annotation.*;

@Documented //사용자 정의 어노테이션 생성
@Constraint(validatedBy = CategoriesExistValidator.class) // 자바에서 제공하는 validation을 사용자가 커스텀
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER }) // 어노테이션 적용 범위 지정
@Retention(RetentionPolicy.RUNTIME) // 어노테이션의 생명 주기 지정
public @interface ExistCategories {

    String message() default "{해당 카테고리가 존재하지 않습니다.}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
