package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserReview;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {

    Page<UserReview> findAllByStore(Store store, Pageable pageRequest);

    Page<UserReview> findAllByUser(User user, Pageable pageRequest);
}
