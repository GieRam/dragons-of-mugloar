package dragons.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import dragons.data.Dragon;
import dragons.data.Game;
import dragons.data.Result;
import dragons.data.Solution;
import dragons.exceptions.FailedGameException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GameClient {

    // TODO: Exception handling

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
        HttpResponse response = httpClient.execute(new HttpGet(gameUrl));

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new FailedGameException();
        }

        return objectMapper.readValue(response.getEntity().getContent(), Game.class);
    }

    public Result putSolution(Integer gameId) throws Exception {
        HttpPut request = new HttpPut(gameUrl + "/" + gameId + "/solution");

        String solutionJson = objectMapper.writeValueAsString(
                new Solution(
                        new Dragon(5, 5, 5, 5)));

        request.addHeader("content-type", "application/json");
        request.setEntity(new StringEntity(solutionJson));

        HttpResponse response = httpClient.execute(request);

        return objectMapper.readValue(response.getEntity().getContent(), Result.class);
    }
}
