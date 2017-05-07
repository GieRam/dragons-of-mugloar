package dragons.engine.printers;

import dragons.engine.calculators.ResultsCalculator;
import dragons.entities.game.Game;
import dragons.entities.game.GameOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BasicPrinter implements Printer {

    private ResultsCalculator resultsCalculator;

    @Autowired
    public BasicPrinter(ResultsCalculator resultsCalculator) {
        this.resultsCalculator = resultsCalculator;
    }

    @Override
    public void print(Map<Game, GameOutput> gameOutputs) {
        System.out.println("Result for " + gameOutputs.size() + " games played:");
        System.out.println(resultsCalculator.calculateWinRatio(gameOutputs) + "% win ratio");
    }
}
