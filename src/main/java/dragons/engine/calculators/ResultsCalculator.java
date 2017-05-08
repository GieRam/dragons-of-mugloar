package dragons.engine.calculators;

import dragons.entities.game.Game;
import dragons.entities.game.GameOutput;
import dragons.entities.game.Result;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ResultsCalculator {

    public int calculateWinRatio(Map<Game, GameOutput> gameOutputs) {
        List<Result> results = collectResultsFrom(gameOutputs);
        double victories = results.stream().filter(this::isVictory).count();
        double ratio = victories / gameOutputs.size() * 100;
        return (int) ratio;
    }

    private List<Result> collectResultsFrom(Map<Game, GameOutput> gameOutputs) {
        return gameOutputs.values()
                .stream()
                .map(GameOutput::getResult)
                .collect(Collectors.toList());
    }

    private boolean isVictory(Result result) {
        return "Victory".equals(result.getStatus());
    }
}
