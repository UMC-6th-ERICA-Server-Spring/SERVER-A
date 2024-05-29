package mission.demo.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mission.demo.apiPayload.code.*;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason(){
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}
