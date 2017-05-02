package dragons.clients.game

import dragons.clients.GameClient
import dragons.data.Game
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
class GameClientSpec extends Specification {

    @Autowired
    GameClient gameClient

    def 'should return valid Game object'() {
        when:
            Game result = gameClient.getGame()
        then:
            result.gameId
            result.knight
    }

}
