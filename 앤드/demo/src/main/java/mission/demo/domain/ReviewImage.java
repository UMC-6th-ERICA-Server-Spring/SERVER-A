package mission.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import mission.demo.domain.common.BaseEntity;
import mission.demo.domain.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @Column(columnDefinition = "Text")
    private String imageUrl;




}
