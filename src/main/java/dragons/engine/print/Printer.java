package dragons.engine.print;

import dragons.data.game.Game;
import dragons.data.game.Result;

import java.util.List;
import java.util.Map;

public interface Printer {

    void print(Map<Game, Result> results);

}
