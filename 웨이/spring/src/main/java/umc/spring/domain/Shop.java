package umc.spring.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import umc.spring.domain.common.BaseEntitiy;

import java.time.LocalTime;

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
}
