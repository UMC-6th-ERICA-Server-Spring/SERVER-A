package umc.spring.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

public class StoreRequestDTO {

    @Getter
    public static class StoreJoinDTO {
        @NotBlank
        String name;
        @Size(min = 5, max = 20)
        String address;
        @Size(min = 2, max = 20)
        String region;
    }

    @Getter
    public static class UserReviewCreateDTO {
        @NotBlank
        String body;
        @NotNull @Max(5) @Min(0)
        Float star;
        //List<String> reviewImages;
    }

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
