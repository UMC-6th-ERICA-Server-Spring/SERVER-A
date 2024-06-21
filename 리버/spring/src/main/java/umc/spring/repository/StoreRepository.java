package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import umc.spring.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
