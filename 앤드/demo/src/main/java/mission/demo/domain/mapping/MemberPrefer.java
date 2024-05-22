package mission.demo.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import mission.demo.domain.Member;
import mission.demo.domain.PreferFood;
import mission.demo.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPrefer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prefer_id")
    private PreferFood preferFood;
}
