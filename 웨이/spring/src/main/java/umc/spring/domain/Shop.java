package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntitiy;
import umc.spring.domain.mapping.Review;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity //JPA Entity임을 명시
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Shop extends BaseEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float rate;

    private LocalTime open;

    private LocalTime close;

    private String province; //ex. 경기도, 강원도, 전라북도...

    private String section; //ex. 안산시, 강릉시, 전주시...

    private String address;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();
}
