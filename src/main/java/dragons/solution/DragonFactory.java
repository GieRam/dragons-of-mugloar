package dragons.solution;

import dragons.clients.WeatherClient;
import dragons.data.game.Dragon;
import dragons.data.game.Game;
import dragons.data.weather.WeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.IntStream;

@Component
public class DragonFactory {

    private static final String STORM = "SRO";
    private static final String RAIN = "HVA";
    private static final String LONG_DRY = "T E";

    private static final String SCALE_THICKNESS = "scaleThickness";
    private static final String CLAW_SHARPNESS = "clawSharpness";
    private static final String WING_STRENGTH = "wingStrength";
    private static final String FIRE_BREATH = "fireBreath";

    private WeatherClient weatherClient;

    @Autowired
    public DragonFactory(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public Dragon createDragonFor(Game game) throws Exception {

        // adjust for weather
        WeatherReport report = weatherClient.getWeatherReport(game.getGameId());
        if (RAIN.equals(report.getCode())) {
            return createRainDragon();
        }
        if (LONG_DRY.equals(report.getCode()) || STORM.equals(report.getCode())) {
            return createBalancedDragon();
        }

        // create balancedStats map
        Map<String, Integer> balancedStats = new HashMap<>();
        balancedStats.put(SCALE_THICKNESS, game.getKnight().getAttack());
        balancedStats.put(CLAW_SHARPNESS, game.getKnight().getArmor());
        balancedStats.put(WING_STRENGTH, game.getKnight().getAgility());
        balancedStats.put(FIRE_BREATH, game.getKnight().getEndurance());

        // get max value (vietoj key -> value, gal is karto value gaut..)
        String highestStat = getHighestStatKey(balancedStats);
        Integer maxToAdd = 10 - balancedStats.get(highestStat);

        // init result
        Map<String, Integer> adjustedStats = new HashMap<>();

        // init loop count for dividing what's left
        int added;

        // adjust highest stat for win condition
        if (maxToAdd >= 3) {
            added = 3;
            adjustedStats.put(highestStat, balancedStats.get(highestStat) + 3);
            balancedStats.remove(highestStat);
        } else {
            added = maxToAdd;
            adjustedStats.put(highestStat, balancedStats.get(highestStat) + maxToAdd);
            balancedStats.remove(highestStat);
        }

        // adjust for anorexia
        Optional<String> zeroStatKey = getZeroStatKey(balancedStats);
        zeroStatKey.ifPresent(balancedStats::remove);
        if (zeroStatKey.isPresent()) {
            highestStat = getHighestStatKey(balancedStats);
            adjustedStats.put(highestStat, balancedStats.get(highestStat) - 2);
            balancedStats.remove(highestStat);
        }

        // adjust what's left
        IntStream.range(0, added).forEach((index) -> {
            String highestStatKey = getHighestStatKey(balancedStats);
            adjustedStats.put(highestStatKey, balancedStats.get(highestStatKey) - 1);
            balancedStats.remove(highestStatKey);
        });

        // add what's left
        adjustedStats.putAll(balancedStats);

        return new Dragon(adjustedStats.get(SCALE_THICKNESS),
                adjustedStats.get(CLAW_SHARPNESS),
                adjustedStats.get(WING_STRENGTH),
                adjustedStats.get(FIRE_BREATH));
    }

    private String getHighestStatKey(Map<String, Integer> stats) {
        return Collections.max(stats.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }

    private Optional<String> getZeroStatKey(Map<String, Integer> stats) {
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            if (entry.getValue() == 0) {
                return Optional.of(entry.getKey());
            }
        }
        return Optional.empty();
    }

    private Dragon createRainDragon() {
        return new Dragon(5,10,5,0);
    }

    private Dragon createBalancedDragon() {
        return new Dragon(5, 5, 5, 5);
    }
}
