package dragons.engine;

import dragons.clients.GameClient;
import dragons.data.Game;
import dragons.data.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class GameEngine {

    private GameClient gameClient;

    @Autowired
    public GameEngine(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    public String playGame(int times) {
        List<Result> results = getResults(times);
        double victories = results.stream().filter(this::isVictory).count();
        Double result = victories / times * 100;
        return result.intValue() + "%";
    }

    private boolean isVictory(Result result) {
        return result.getStatus().equals("Victory");
    }

    private List<Result> getResults(int times) {
        List<Result> results = new ArrayList<>();
        IntStream.rangeClosed(1, times).forEach((index) -> {
            try {
                Game game = gameClient.getGame();
                Result result = gameClient.putSolution(game.getGameId());
                results.add(result);
            } catch (Exception ignored) {
            }
        });
        return results;
    }

}
