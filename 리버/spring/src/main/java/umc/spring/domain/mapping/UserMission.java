package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.MissionStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_Id")
    private Mission mission;

    public void setUser(User user) {
        if (this.user != null) {
            user.getUserMissionList().remove(this);
        }
        this.user = user;
        user.getUserMissionList().add(this);
    }

    public void setMission(Mission mission) {
        if (this.mission != null)
            mission.getUserMissionList().remove(this);
        this.mission = mission;
        mission.getUserMissionList().add(this);
    }
}
