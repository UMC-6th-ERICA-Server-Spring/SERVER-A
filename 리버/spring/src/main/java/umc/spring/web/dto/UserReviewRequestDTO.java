package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

public class UserReviewRequestDTO {

    @Getter
    public static class UserReviewCreateDTO {
        @NotBlank
        String body;
        @NotNull
        Float star;
        //List<String> reviewImages;
    }
}
