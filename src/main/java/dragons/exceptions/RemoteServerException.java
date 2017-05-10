package dragons.exceptions;

public class RemoteServerException extends Exception {

    public RemoteServerException() {
        super("Error response from remote server");
    }
}

