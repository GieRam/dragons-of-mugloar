package dragons.engine.printers;

import dragons.engine.calculators.ResultsCalculator;
import dragons.entities.game.Game;
import dragons.entities.game.GameOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DetailedPrinter implements Printer {

    private ResultsCalculator resultsCalculator;

    @Autowired
    public DetailedPrinter(ResultsCalculator resultsCalculator) {
        this.resultsCalculator = resultsCalculator;
    }

    @Override
    public void print(Map<Game, GameOutput> gameOutputs) {
        System.out.println("=====================================================");
        System.out.println("Games count: " + gameOutputs.size() + ", win ratio: " + resultsCalculator.calculateWinRatio(gameOutputs) + "%");
        System.out.println("=====================================================");
        System.out.println("");
        System.out.println("Detailed outcome: ");

        gameOutputs.forEach((key, value) -> {
            System.out.println("====== Game ID: " + key.getGameId() + " =======");
            System.out.println(value.getResult());
            System.out.println(key.getKnight());
            System.out.println(value.getDragon());
        });
    }
}
