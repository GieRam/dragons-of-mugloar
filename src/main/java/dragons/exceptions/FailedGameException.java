package dragons.exceptions;

public class FailedGameException extends Exception {

    public FailedGameException() {
        super("Failed to get game");
    }
}

