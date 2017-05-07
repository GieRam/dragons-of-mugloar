package dragons.factories;

import dragons.clients.WeatherClient;
import dragons.entities.game.Dragon;
import dragons.entities.game.Game;
import dragons.entities.weather.WeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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

    public Dragon createDragonFor(Game game) {

        // adjust for weather
        WeatherReport report = null;
        try {
            report = weatherClient.getWeatherReport(game.getGameId());
        } catch (Exception e) {
            return null;
        }
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
        if (zeroStatKey.isPresent()) {
            adjustedStats.put(zeroStatKey.get(), balancedStats.get(zeroStatKey.get()));
            balancedStats.remove(zeroStatKey.get());

            highestStat = getHighestStatKey(balancedStats);
            adjustedStats.put(highestStat, balancedStats.get(highestStat) - 2);
            balancedStats.remove(highestStat);
            added = added - 2;
        }

        // adjust what's left
        for (; added > 0; added--) {
            if (balancedStats.size() == 0) {
                break;
            }
            String highestStatKey = getHighestStatKey(balancedStats);
            adjustedStats.put(highestStatKey, balancedStats.get(highestStatKey) - 1);
            balancedStats.remove(highestStatKey);
        }

        // add what's left
        adjustedStats.putAll(balancedStats);

        return new Dragon(adjustedStats.get(SCALE_THICKNESS),
                            adjustedStats.get(CLAW_SHARPNESS),
                            adjustedStats.get(WING_STRENGTH),
                            adjustedStats.get(FIRE_BREATH));
    }

    private String getHighestStatKey(Map<String, Integer> stats) {
        if (stats.size() == 1) {
            return stats.entrySet().iterator().next().getKey();
        }
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
        return new Dragon(5, 10, 5, 0);
    }

    private Dragon createBalancedDragon() {
        return new Dragon(5, 5, 5, 5);
    }
}
