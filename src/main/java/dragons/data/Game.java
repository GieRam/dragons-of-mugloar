package dragons.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Game {

    private Long gameId;
    private Knight knight;

    public Game(@JsonProperty("attack") Long gameId,
                @JsonProperty("knight") Knight knight) {
        this.gameId = gameId;
        this.knight = knight;
    }

    public Long getGameId() {
        return gameId;
    }

    public Knight getKnight() {
        return knight;
    }
}
