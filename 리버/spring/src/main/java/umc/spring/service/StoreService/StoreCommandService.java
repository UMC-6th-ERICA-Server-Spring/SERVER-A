package umc.spring.service.StoreService;

import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    @Transactional
    Store joinStore(StoreRequestDTO.StoreJoinDTO request);
}
