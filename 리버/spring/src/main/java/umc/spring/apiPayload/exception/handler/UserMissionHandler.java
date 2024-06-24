package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.exception.GeneralException;

public class UserMissionHandler extends GeneralException {

    public UserMissionHandler(BaseErrorCode code) {
        super(code);
    }
}
