package dragons.engine;

import dragons.data.game.Result;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultsCalculator {

    public int calculateWinRatio(List<Result> results) {
        double victories = results.stream().filter(this::isVictory).count();
        double ratio = victories / results.size() * 100;
        return (int) ratio;
    }

    private boolean isVictory(Result result) {
        return "Victory".equals(result.getStatus());
    }

}
