package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.validation.annotation.IfOngoing;

@Component
@RequiredArgsConstructor
public class MissionOngoingValidator implements ConstraintValidator<IfOngoing, String> {

    private String missionStatus;

    @Override
    public void initialize(IfOngoing constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        missionStatus = constraintAnnotation.missionStatus();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (missionStatus.equals("ONGOING")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ONGOING.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
