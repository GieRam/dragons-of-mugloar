package dragons.solution;

import dragons.clients.WeatherClient;
import dragons.data.game.Dragon;
import dragons.data.game.Game;
import dragons.data.weather.WeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DragonFactory {

    private WeatherClient weatherClient;

    @Autowired
    public DragonFactory(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public Dragon createDragonFor(Game game) throws Exception {
        WeatherReport report = weatherClient.getWeatherReport(game.getGameId());
        if ("HVA".equals(report.getCode())) {
            return new Dragon(5,10,5,0);
        }
        return new Dragon(5,5,5,5);
    }
}
