package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionRequesetDTO {

    @Getter
    public static class MissionCreateDTO {
        @NotNull
        LocalDate deadline;
        @NotNull
        Integer price;
        @NotNull
        Integer point;
    }
}
