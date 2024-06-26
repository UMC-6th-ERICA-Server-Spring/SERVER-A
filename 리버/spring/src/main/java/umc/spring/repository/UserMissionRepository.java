package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserMission;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    UserMission findByMissionAndUser(Mission mission, User user);

    Page<UserMission> findAllByUser(User user, Pageable pageRequest);
}
