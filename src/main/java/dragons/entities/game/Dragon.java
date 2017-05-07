package dragons.entities.game;

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

    public void setScaleThickness(Integer scaleThickness) {
        this.scaleThickness = scaleThickness;
    }

    public Integer getClawSharpness() {
        return clawSharpness;
    }

    public void setClawSharpness(Integer clawSharpness) {
        this.clawSharpness = clawSharpness;
    }

    public Integer getWingStrength() {
        return wingStrength;
    }

    public void setWingStrength(Integer wingStrength) {
        this.wingStrength = wingStrength;
    }

    public Integer getFireBreath() {
        return fireBreath;
    }

    public void setFireBreath(Integer fireBreath) {
        this.fireBreath = fireBreath;
    }

    @Override
    public String toString() {
        return "Dragon: "
                + "scaleThickness: " + scaleThickness
                + ", clawSharpness: " + clawSharpness
                + ", wingStrength: " + wingStrength
                + ", fireBreath: " + fireBreath;
    }
}
