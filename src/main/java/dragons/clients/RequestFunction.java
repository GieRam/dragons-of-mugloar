package dragons.clients;

import org.apache.http.HttpResponse;

public interface RequestFunction {

    HttpResponse apply() throws Exception;

}
