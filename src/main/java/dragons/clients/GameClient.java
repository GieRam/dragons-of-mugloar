package dragons.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import dragons.data.Game;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GameClient {

    private HttpClient httpClient;
    private ObjectMapper objectMapper;
    private String gameUrl;

    @Autowired
    public GameClient(HttpClient httpClient,
                      ObjectMapper objectMapper,
                      @Value("${dragons.game.url}") String gameUrl) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.gameUrl = gameUrl;
    }

    public Game getGame() throws IOException {
        HttpGet request = new HttpGet(gameUrl);
        request.addHeader("accept", "application/json");

        HttpResponse response = httpClient.execute(request);

        // TODO: handle != 200

        return objectMapper.readValue(response.getEntity().getContent(), Game.class);
    }
}
