package dragons.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import dragons.entities.game.Dragon;
import dragons.entities.game.Game;
import dragons.entities.game.Result;
import dragons.entities.game.Solution;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GameClient extends AbstractClient {

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

    public Game getGame() throws Exception {
        HttpResponse response = managedRequest(() -> {
            HttpGet request = new HttpGet(gameUrl);
            return httpClient.execute(request);
        });
        return objectMapper.readValue(response.getEntity().getContent(), Game.class);
    }

    public Result putSolution(Integer gameId, Dragon dragon) throws Exception {
        HttpResponse response = managedRequest(() -> {
            HttpPut request = new HttpPut(gameUrl + "/" + gameId + "/solution");
            request.addHeader("content-type", "application/json");

            String solutionJson = objectMapper.writeValueAsString(new Solution(dragon));
            request.setEntity(new StringEntity(solutionJson));

            return httpClient.execute(request);
        });
        return objectMapper.readValue(response.getEntity().getContent(), Result.class);
    }
}
