package dragons.data.game;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dragon {

    private Integer scaleThickness;
    private Integer clawSharpness;
    private Integer wingStrength;
    private Integer fireBreath;

    public Dragon(@JsonProperty("scaleThickness") Integer scaleThickness,
                  @JsonProperty("clawSharpness") Integer clawSharpness,
                  @JsonProperty("wingStrength") Integer wingStrength,
                  @JsonProperty("fireBreath") Integer fireBreath) {
        this.scaleThickness = scaleThickness;
        this.clawSharpness = clawSharpness;
        this.wingStrength = wingStrength;
        this.fireBreath = fireBreath;
    }

    public Integer getScaleThickness() {
        return scaleThickness;
    }

    public Integer getClawSharpness() {
        return clawSharpness;
    }

    public Integer getWingStrength() {
        return wingStrength;
    }

    public Integer getFireBreath() {
        return fireBreath;
    }
}
