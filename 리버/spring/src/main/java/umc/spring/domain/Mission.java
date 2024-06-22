package umc.spring.domain;


import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.mapping.UserMission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate deadline;

    private Integer price;

    private Integer point;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserMission> userMissionList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public void setStore(Store store) {
        if (this.store != null)
            store.getMissionList().remove(this);
        this.store = store;
        store.getMissionList().add(this);
    }

}
