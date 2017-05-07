package dragons.entities.game;

public class GameOutput {

    private Result result;

    private Dragon dragon;

    public GameOutput(Result result,
                      Dragon dragon) {
        this.result = result;
        this.dragon = dragon;
    }

    public Result getResult() {
        return result;
    }

    public Dragon getDragon() {
        return dragon;
    }
}
