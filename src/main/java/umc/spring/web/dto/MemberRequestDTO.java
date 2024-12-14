package umc.spring.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc.spring.domain.enums.Role;

import java.util.List;

public class MemberRequestDTO {
    @Getter
    @Setter   // thymeleaf에서 사용하기 위해 추가
    public static class JoinDto{
        @NotBlank
        String name;

        @NotBlank
        @Email
        String email;

        @NotBlank
        String password;

        @NotNull
        Integer gender;

        @NotNull
        Integer birthYear;

        @NotNull
        Integer birthMonth;

        @NotNull
        Integer birthDay;

        @Size(min=5, max=12)
        String address;

        @Size(min=5, max=12)
        String specAddress;

        List<Long> preferCategory;

        @NotNull
        Role role;
    }
}
