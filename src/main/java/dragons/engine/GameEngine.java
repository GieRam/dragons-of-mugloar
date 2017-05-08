package dragons.engine;

import dragons.clients.GameClient;
import dragons.engine.printers.Printer;
import dragons.entities.game.Dragon;
import dragons.entities.game.Game;
import dragons.entities.game.GameOutput;
import dragons.entities.game.Result;
import dragons.factories.DragonFactory;
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
        Map<Game, GameOutput> gameOutputs = playGameFor(times);
        printer.print(gameOutputs);
    }

    private Map<Game, GameOutput> playGameFor(int times) {
        Map<Game, GameOutput> gameOutputs = new HashMap<>();
        IntStream.range(0, times).forEach((index) -> {
            try {
                Game game = gameClient.getGame();
                Dragon dragon = dragonFactory.createDragonFor(game);
                Result result = gameClient.putSolution(game.getGameId(), dragon);
                gameOutputs.put(game, new GameOutput(result, dragon));
            } catch (Exception ex) {
                ex.printStackTrace();
                // TODO: Implement fallback strategy
            }
        });
        return gameOutputs;
    }

}
