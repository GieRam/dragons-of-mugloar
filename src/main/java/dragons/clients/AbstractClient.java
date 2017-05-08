package dragons.clients;

import dragons.exceptions.RemoteServerException;
import org.apache.http.HttpResponse;

public abstract class AbstractClient {

    protected HttpResponse managedRequest(RequestFunction requestFunction) throws Exception {
        HttpResponse response = requestFunction.apply(); // handle IOException
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RemoteServerException();
        }
        return response;
    }
}
