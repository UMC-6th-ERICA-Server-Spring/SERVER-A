package umc.spring.domain.mapping;

import jakarta.persistence.*;
import umc.spring.domain.User;
import umc.spring.domain.common.BaseEntitiy;
import umc.spring.domain.enums.Category;

public class Food extends BaseEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
