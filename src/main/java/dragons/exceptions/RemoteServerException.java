package dragons.exceptions;

public class RemoteServerException extends Exception {

    public RemoteServerException() {
        super("Failed response from remote server");
    }
}

