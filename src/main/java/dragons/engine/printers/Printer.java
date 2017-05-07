package dragons.engine.printers;

import dragons.entities.game.Game;
import dragons.entities.game.GameOutput;

import java.util.Map;

public interface Printer {

    void print(Map<Game, GameOutput> gameOutputs);

}
