package dragons.factories;

import dragons.clients.WeatherClient;
import dragons.entities.game.Dragon;
import dragons.entities.game.Game;
import dragons.entities.weather.WeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static dragons.entities.game.Dragon.*;

@Component
public class DragonFactory {

    private static final String STORM = "SRO";
    private static final String RAIN = "HVA";
    private static final String LONG_DRY = "T E";

    private DragonStatsResolver dragonStatsResolver;
    private WeatherClient weatherClient;

    @Autowired
    public DragonFactory(DragonStatsResolver dragonStatsResolver,
                         WeatherClient weatherClient) {
        this.dragonStatsResolver = dragonStatsResolver;
        this.weatherClient = weatherClient;
    }

    public Dragon createDragonFor(Game game) {
        Optional<Dragon> weatherDragon = createDragonForWeather(game.getGameId());
        if (weatherDragon.isPresent()) {
            return weatherDragon.get().isStub() ? null : weatherDragon.get();
        }

        Map<String, Integer> optimalStats = dragonStatsResolver.getOptimalStatsFor(game);
        return new Dragon(optimalStats.get(SCALE_THICKNESS),
                optimalStats.get(CLAW_SHARPNESS),
                optimalStats.get(WING_STRENGTH),
                optimalStats.get(FIRE_BREATH));
    }

    private Optional<Dragon> createDragonForWeather(Integer gameId) {
        try {
            WeatherReport report = weatherClient.getWeatherReport(gameId);
            if (RAIN.equals(report.getCode())) {
                return Optional.of(createRainDragon());
            }
            if (LONG_DRY.equals(report.getCode())) {
                return Optional.of(createBalancedDragon());
            }
            if (STORM.equals(report.getCode())) {
                return Optional.of(createStubDragon());
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Dragon createStubDragon() {
        return new Dragon(0,0,0,0);
    }

    private Dragon createRainDragon() {
        return new Dragon(5, 10, 5, 0);
    }

    private Dragon createBalancedDragon() {
        return new Dragon(5, 5, 5, 5);
    }
}
