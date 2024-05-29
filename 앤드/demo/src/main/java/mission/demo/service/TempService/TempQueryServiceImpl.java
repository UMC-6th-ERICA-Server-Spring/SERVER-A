package mission.demo.service.TempService;

import lombok.RequiredArgsConstructor;
import mission.demo.apiPayload.code.status.ErrorStatus;
import mission.demo.apiPayload.exception.handler.TempHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService {

    @Override
    public void CheckFlag(Integer flag){
        if(flag==1)
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
    }
}
