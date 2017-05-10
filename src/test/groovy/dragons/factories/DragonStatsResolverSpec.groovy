package dragons.factories

import dragons.entities.game.Game
import dragons.entities.game.Knight
import spock.lang.Specification


class DragonStatsResolverSpec extends Specification {

    DragonStatsResolver dragonStatsResolver

    def 'setup'() {
        dragonStatsResolver = new DragonStatsResolver()
    }

    def 'should get optimal stats for balanced knight'() {
        given:
            Game game = new Game(1, new Knight('Test',5,5,5,5))
        when:
            Map<String, Integer> stats = dragonStatsResolver.getOptimalStatsFor(game)
        then:
            stats.values().sum() == 20
            stats.values().max() == 8
            stats.values().min() == 4
    }

    def 'should get optimal stats for knight with a zero stat'() {
        given:
            Game game = new Game(1, new Knight('Test',8,6,6,0))
        when:
            Map<String, Integer> stats = dragonStatsResolver.getOptimalStatsFor(game)
        then:
            stats.values().sum() == 20
            stats.values().max() == 10
            stats.values().min() == 0
    }

    def 'should get optimal stats for knight with two equal top stats and a zero stat'() {
        given:
            Game game = new Game(1, new Knight('Test', 7,7,6,0))
        when:
            Map<String, Integer> stats = dragonStatsResolver.getOptimalStatsFor(game)
        then:
            stats.values().sum() == 20
            stats.values().max() == 10
            stats.values().min() == 0
    }
}
