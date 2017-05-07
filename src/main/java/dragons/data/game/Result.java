package dragons.data.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    private String status;
    private String message;
    private Dragon dragon;

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

    public Dragon getDragon() {
        return dragon;
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }

    @Override
    public String toString() {
        return "Status: " + status + ", message: " + message;
    }
}
