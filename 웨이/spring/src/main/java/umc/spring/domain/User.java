package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntitiy;
import umc.spring.domain.enums.Gender;

import java.time.LocalDate;

@Entity //JPA Entity임을 명시
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String pwd;

    private String phone;

    private Integer myPoint;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birth;
}
