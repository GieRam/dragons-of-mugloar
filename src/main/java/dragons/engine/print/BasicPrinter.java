package dragons.engine.print;

import dragons.data.game.Game;
import dragons.data.game.Result;
import dragons.engine.ResultsCalculator;
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
    public void print(Map<Game, Result> results) {
        System.out.println("=====================================================");
        System.out.println("Games count: " + results.size() + ", win ratio: " + resultsCalculator.calculateWinRatio(results) + "%");
        System.out.println("=====================================================");
        System.out.println("");
        System.out.println("Detailed outcome: ");

        for (Map.Entry<Game, Result> entry : results.entrySet()) {
            System.out.println("====== Game ID: " + entry.getKey().getGameId() + " =======");
            System.out.println(entry.getValue());
            System.out.println(entry.getKey().getKnight());
            System.out.println(entry.getValue().getDragon());
        }
    }
}
