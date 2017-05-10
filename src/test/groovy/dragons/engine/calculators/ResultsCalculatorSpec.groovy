package dragons.engine.calculators

import dragons.entities.game.Game
import dragons.entities.game.GameOutput
import dragons.entities.game.Result
import spock.lang.Specification


class ResultsCalculatorSpec extends Specification {

    ResultsCalculator resultsCalculator

    def 'setup'() {
        resultsCalculator = new ResultsCalculator()
    }

    def 'should calculate expected win ratio'() {
        given:
            Map<Game, GameOutput> gameOutputs = createGameResults()
        when:
            int winRatio = resultsCalculator.calculateWinRatio(gameOutputs)
        then:
            winRatio == 50
    }

    def createGameResults() {
        Map<Game, GameOutput> results = new HashMap()
        5.times { results.put(new Game(), createGameOutput("Victory")) }
        5.times { results.put(new Game(), createGameOutput("Defeat")) }
        return results
    }

    def createGameOutput(String resultStatus) {
        return new GameOutput(
                new Result(resultStatus, null),
                null
        )
    }
}
