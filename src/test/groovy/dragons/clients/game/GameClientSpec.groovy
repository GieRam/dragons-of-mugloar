package dragons.clients.game

import com.fasterxml.jackson.databind.ObjectMapper
import dragons.clients.GameClient
import dragons.data.Game
import org.apache.http.HttpResponse
import org.apache.http.StatusLine
import org.apache.http.impl.client.CloseableHttpClient
import spock.lang.Specification

class GameClientSpec extends Specification {

//    def gameClient
//
//    CloseableHttpClient httpClient
//
//    def objectMapper
//
//    def gameUrl
//
//    def 'setup'() {
//        httpClient = Mock(CloseableHttpClient)
//        objectMapper = Mock(ObjectMapper)
//        gameUrl = "http://www.dragonsofmugloar.com/api/game"
//
//        gameClient = new GameClient(httpClient, objectMapper,"http://www.dragonsofmugloar.com/api/game")
//    }
//
//    def 'should execute http request and read response value on status 200'() {
//        given:
//            def response = Stub(HttpResponse)
//            def statusLine = Stub(StatusLine)
//            response.getStatusLine() >> statusLine
//            statusLine.getStatusCode() >> 200
//
//            httpClient.execute(_) >> response
//        when:
//            gameClient.getGame()
//        then:
//            1 * httpClient.execute(_)
//            1 * objectMapper.readValue(_, Game)
//    }

}
