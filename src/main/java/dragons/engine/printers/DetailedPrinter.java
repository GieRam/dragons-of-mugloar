package dragons.engine.printers;

import dragons.engine.calculators.ResultsCalculator;
import dragons.entities.game.Game;
import dragons.entities.game.GameOutput;

import java.util.Map;
import java.util.function.BiConsumer;

public class DetailedPrinter implements Printer {

    private ResultsCalculator resultsCalculator;

    public DetailedPrinter(ResultsCalculator resultsCalculator) {
        this.resultsCalculator = resultsCalculator;
    }

    @Override
    public void print(Map<Game, GameOutput> gameOutputs) {
        System.out.println("=====================================================");
        System.out.printf("Games count: %d, win ratio: %d%%\n", gameOutputs.size(), resultsCalculator.calculateWinRatio(gameOutputs));
        System.out.println("=====================================================");
        System.out.println();
        System.out.println("Detailed outcome log: ");

        gameOutputs.forEach(printGameLog());
    }

    private BiConsumer<Game, GameOutput> printGameLog() {
        return (game, gameOutput) -> {
            System.out.printf("====== Game ID: %d =======\n", game.getGameId());
            System.out.println(gameOutput.getResult());
            System.out.println(game.getKnight());
            System.out.println(gameOutput.getDragon());
        };
    }
}
