package app.tca.thecloutapp.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse<T> extends ResponseModel {
    private T result;
    private String token;

    public SuccessResponse(Boolean success, String message, T result ) {
        super(success,message);
        this.result = result;
    }

    public SuccessResponse(Boolean success, String message, String token, T result ) {
        super(success,message);
        this.result = result;
        this.token = token;
    }

    public SuccessResponse(Boolean success, String message, String token ) {
        super(success,message);
        this.token = token;
    }
    public SuccessResponse(Boolean success, String message ) {
        super(success,message);
    }
}
