package dragons.engine;

import dragons.clients.GameClient;
import dragons.data.game.Dragon;
import dragons.data.game.Game;
import dragons.data.game.Result;
import dragons.solution.DragonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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
        List<Result> results = playGameFor(times);
        printer.print(results);
    }

    private List<Result> playGameFor(int times) {
        List<Result> results = new ArrayList<>();
        IntStream.rangeClosed(1, times).forEach((index) -> {
            try {
                Game game = gameClient.getGame();
                Dragon dragon = dragonFactory.createDragonFor(game);
                Result result = gameClient.putSolution(game.getGameId(), dragon);
                results.add(result);
            } catch (Exception ignored) {
            }
        });
        return results;
    }

}
