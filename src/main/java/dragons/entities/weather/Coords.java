package dragons.entities.weather;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Coords {

    private double x;
    private double y;
    private double z;

    public Coords(@JacksonXmlProperty(localName = "x") double x,
                  @JacksonXmlProperty(localName = "y") double y,
                  @JacksonXmlProperty(localName = "z") double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
