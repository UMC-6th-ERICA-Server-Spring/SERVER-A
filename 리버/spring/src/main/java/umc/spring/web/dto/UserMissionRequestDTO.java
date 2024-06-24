package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class UserMissionRequestDTO {

    @Getter
    public static class UserMissionChallengeDTO {
        @NotNull
        Long status;
    }
}
