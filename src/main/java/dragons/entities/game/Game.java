package dragons.entities.game;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Game {

    private Integer gameId;
    private Knight knight;

    public Game() {
    }

    public Game(@JsonProperty("attack") Integer gameId,
                @JsonProperty("knight") Knight knight) {
        this.gameId = gameId;
        this.knight = knight;
    }

    public Integer getGameId() {
        return gameId;
    }

    public Knight getKnight() {
        return knight;
    }
}
