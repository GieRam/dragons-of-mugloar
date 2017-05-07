package dragons.data.game;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {

    private String status;
    private String message;

    public Result(@JsonProperty("status") String status,
                  @JsonProperty("message") String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
