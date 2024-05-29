package mission.demo.apiPayload.exception.handler;

import mission.demo.apiPayload.code.BaseErrorCode;
import mission.demo.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode){
        super(errorCode);
    }
}
