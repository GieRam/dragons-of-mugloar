package dragons.factories;

import dragons.entities.game.Game;
import org.springframework.stereotype.Component;

import java.util.*;

import static dragons.entities.game.Dragon.*;

@Component
public class DragonStatsResolver {

    private static final int STAT_LIMIT = 10;
    private static final int WIN_THRESHOLD = 3;

    public Map<String, Integer> getOptimalStatsFor(Game game) {
        Map<String, Integer> equivalentStats = getEquivalentStatsFor(game);
        Map<String, Integer> optimalStats = new HashMap<>();

        int statsAdded = addToHighestStat(equivalentStats, optimalStats);
        adjustForAnorexia(equivalentStats, optimalStats);
        adjustForStatsIncrease(equivalentStats, optimalStats, statsAdded);
        optimalStats.putAll(equivalentStats);

        return optimalStats;
    }

    private Map<String, Integer> getEquivalentStatsFor(Game game) {
        Map<String, Integer> balancedStats = new HashMap<>();
        balancedStats.put(SCALE_THICKNESS, game.getKnight().getAttack());
        balancedStats.put(CLAW_SHARPNESS, game.getKnight().getArmor());
        balancedStats.put(WING_STRENGTH, game.getKnight().getAgility());
        balancedStats.put(FIRE_BREATH, game.getKnight().getEndurance());
        return balancedStats;
    }

    private int addToHighestStat(Map<String, Integer> equivalentStats,
                                 Map<String, Integer> optimalStats) {
        String highestStat = getHighestStatKey(equivalentStats);
        Integer maxToAdd = STAT_LIMIT - equivalentStats.get(highestStat);

        if (maxToAdd >= WIN_THRESHOLD) {
            optimalStats.put(highestStat, equivalentStats.get(highestStat) + WIN_THRESHOLD);
            equivalentStats.remove(highestStat);
            return WIN_THRESHOLD;
        } else {
            optimalStats.put(highestStat, equivalentStats.get(highestStat) + maxToAdd);
            equivalentStats.remove(highestStat);
            return maxToAdd;
        }
    }

    private void adjustForStatsIncrease(Map<String, Integer> balancedStats,
                                        Map<String, Integer> adjustedStats,
                                        int added) {
        for (; added > 0; added--) {
            String highestStatKey = getHighestStatKey(balancedStats);
            adjustedStats.put(highestStatKey, balancedStats.get(highestStatKey) - 1);
            if (added > balancedStats.size()) {
                balancedStats.put(highestStatKey, balancedStats.get(highestStatKey) - 1);
                continue;
            }
            balancedStats.remove(highestStatKey);
        }
    }

    private void adjustForAnorexia(Map<String, Integer> balancedStats, Map<String, Integer> adjustedStats) {
        Optional<String> zeroStatKey = getZeroStatKey(balancedStats);
        if (zeroStatKey.isPresent()) {
            adjustedStats.put(zeroStatKey.get(), balancedStats.get(zeroStatKey.get()));
            balancedStats.remove(zeroStatKey.get());
        }
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
}
