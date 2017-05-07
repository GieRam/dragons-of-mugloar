package dragons.data.weather;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "report")
public class WeatherReport {

    private String time;

    private Coords coords;

    private String code;

    private String message;

    private Double varXRating;

    public WeatherReport(@JacksonXmlProperty(localName = "time") String time,
                         @JacksonXmlProperty(localName = "coords") Coords coords,
                         @JacksonXmlProperty(localName = "code") String code,
                         @JacksonXmlProperty(localName = "message") String message,
                         @JacksonXmlProperty(localName = "varX-Rating") Double varXRating) {
        this.time = time;
        this.coords = coords;
        this.code = code;
        this.message = message;
        this.varXRating = varXRating;
    }

    public String getTime() {
        return time;
    }

    public Coords getCoords() {
        return coords;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Double getVarXRating() {
        return varXRating;
    }
}
