package umc.spring.domain.mapping;

import jakarta.persistence.*;
import umc.spring.domain.Shop;
import umc.spring.domain.User;
import umc.spring.domain.common.BaseEntitiy;

public class Review extends BaseEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Float rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
