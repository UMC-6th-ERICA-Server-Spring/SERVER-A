package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.domain.mapping.UserMission;
import umc.spring.validation.validator.MissionOngoingValidator;

import java.lang.annotation.*;

@Documented //사용자 정의 어노테이션 생성
@Constraint(validatedBy = MissionOngoingValidator.class) // 자바에서 제공하는 validation을 사용자가 커스텀
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER }) // 어노테이션 적용 범위 지정
@Retention(RetentionPolicy.RUNTIME) // 어노테이션의 생명 주기 지정
public @interface IfOngoing {

    String message() default "{이미 도전 중인 미션입니다.}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String missionStatus();
}
