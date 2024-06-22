package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.domain.Mission;
import umc.spring.validation.annotation.ExistCategories;
import umc.spring.validation.annotation.ExistMission;

import java.util.List;

public class UserRequestDTO {

    @Getter
    public static class UserJoinDTO {
        @NotBlank
        String name;
        @NotNull
        Integer gender;
        @NotNull
        Integer birthYear;
        @NotNull
        Integer birthMonth;
        @NotNull
        Integer birthDay;
        @Size(min = 5, max = 20)
        String phoneNumber;
        @Size(min = 5, max = 50)
        String address;
        @Size(min = 5, max = 20)
        String specAddress;
        @ExistCategories
        List<Long> userFood;
    }
}
