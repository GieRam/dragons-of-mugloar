package dragons.factories

import dragons.clients.WeatherClient
import dragons.entities.game.Dragon
import dragons.entities.game.Game
import dragons.entities.game.Knight
import dragons.entities.weather.WeatherReport
import spock.lang.Specification


class DragonFactorySpec extends Specification {

    DragonFactory dragonFactory

    Game game

    WeatherClient weatherClient

    def 'setup'() {
        game = new Game(1, new Knight('Sir Test',4,7,4,5))
        weatherClient = Stub(WeatherClient)
        dragonFactory = new DragonFactory(new DragonStatsResolver(), weatherClient)
    }

    def 'should create balanced dragon for long dry weather'() {
        given:
            weatherClient.getWeatherReport(1) >> createWeatherReport(WeatherReport.LONG_DRY)
        when:
            Dragon dragon = dragonFactory.createDragonFor(game)
        then:
            dragon.clawSharpness == 5
            dragon.scaleThickness == 5
            dragon.wingStrength == 5
            dragon.fireBreath == 5
    }

    def 'should return null for storm weather'() {
        given:
            weatherClient.getWeatherReport(1) >> createWeatherReport(WeatherReport.STORM)
        when:
            Dragon dragon = dragonFactory.createDragonFor(game)
        then:
            dragon == null
    }

    def 'should return 10 claw 0 fire dragon for rainy weather'() {
        given:
            weatherClient.getWeatherReport(1) >> createWeatherReport(WeatherReport.RAIN)
        when:
            Dragon dragon = dragonFactory.createDragonFor(game)
        then:
            dragon.clawSharpness == 10
            dragon.scaleThickness == 5
            dragon.wingStrength == 5
            dragon.fireBreath == 0
    }

    def 'should return optimal stats for normal weather'() {
        given:
            weatherClient.getWeatherReport(1) >> createWeatherReport(WeatherReport.NORMAL)
        when:
            Dragon dragon = dragonFactory.createDragonFor(game)
        then:
            dragon.clawSharpness == 10
            dragon.scaleThickness == 3
            dragon.wingStrength == 3
            dragon.fireBreath == 4
    }

    WeatherReport createWeatherReport(String weatherCode) {
        return new WeatherReport(null, null, weatherCode, null, null);
    }
}
