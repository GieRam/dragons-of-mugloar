package dragons.clients;

import dragons.exceptions.FailedGameException;
import org.apache.http.HttpResponse;

public abstract class AbstractClient {

    protected HttpResponse managedRequest(RequestFunction requestFunction) throws Exception {
        HttpResponse response = requestFunction.apply();
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new FailedGameException();
        }
        return response;
    }
}
