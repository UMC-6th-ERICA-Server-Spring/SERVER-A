package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

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
}
