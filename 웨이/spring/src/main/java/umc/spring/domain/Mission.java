package umc.spring.domain;

import jakarta.persistence.*;
import umc.spring.domain.common.BaseEntitiy;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDate;

public class Mission extends BaseEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer price; //미션 금액 → 10,000(원)

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    private LocalDate deadline;

    private Integer reward;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
