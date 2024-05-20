package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntitiy;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.mapping.Food;
import umc.spring.domain.mapping.Review;
import umc.spring.domain.mapping.UserMission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Food> foodList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMission> userMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();
}
