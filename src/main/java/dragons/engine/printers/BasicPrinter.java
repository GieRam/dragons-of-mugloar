package dragons.engine.printers;

import dragons.engine.calculators.ResultsCalculator;
import dragons.entities.game.Game;
import dragons.entities.game.GameOutput;

import java.util.Map;

public class BasicPrinter implements Printer {

    private ResultsCalculator resultsCalculator;

    public BasicPrinter(ResultsCalculator resultsCalculator) {
        this.resultsCalculator = resultsCalculator;
    }

    @Override
    public void print(Map<Game, GameOutput> gameOutputs) {
        System.out.printf("Result for %d games played:\n", gameOutputs.size());
        System.out.printf("%d%% win ratio\n", resultsCalculator.calculateWinRatio(gameOutputs));
    }
}
