package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.UserReview;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
}
