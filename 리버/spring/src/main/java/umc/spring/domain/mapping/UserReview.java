package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import umc.spring.domain.ReviewImage;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.domain.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnDefault("0.0")
    private Float star;

    @Column(nullable = false, length = 100)
    private String body;

    @OneToMany(mappedBy = "userReview", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ReviewImage> reviewImages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public void setUser(User user) {
        if (this.user != null)
            user.getUserReviewList().remove(this);
        this.user = user;
        user.getUserReviewList().add(this);
    }

    public void setStore(Store store) {
        if (this.store != null)
            store.getUserReviewList().remove(this);
        this.store = store;
        store.getUserReviewList().add(this);
    }
}
