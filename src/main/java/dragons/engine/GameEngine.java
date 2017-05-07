package dragons.engine;

import dragons.clients.GameClient;
import dragons.data.game.Dragon;
import dragons.data.game.Game;
import dragons.data.game.Result;
import dragons.engine.print.Printer;
import dragons.solution.DragonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class GameEngine {

    private GameClient gameClient;

    private Printer printer;

    private DragonFactory dragonFactory;

    @Autowired
    public GameEngine(GameClient gameClient,
                      Printer printer,
                      DragonFactory dragonFactory) {
        this.gameClient = gameClient;
        this.printer = printer;
        this.dragonFactory = dragonFactory;
    }

    public void playGame(int times) {
        Map<Game, Result> results = playGameFor(times);
        printer.print(results);
    }

    private Map<Game, Result> playGameFor(int times) {
        Map<Game, Result> results = new HashMap<>();
        IntStream.rangeClosed(1, times).forEach((index) -> {
            try {
                Game game = gameClient.getGame();
                Dragon dragon = dragonFactory.createDragonFor(game);
                Result result = gameClient.putSolution(game.getGameId(), dragon);
                result.setDragon(dragon);
                results.put(game, result);
            } catch (Exception ignored) {
            }
        });
        return results;
    }

}
