package dragons.engine;

import dragons.data.game.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BasicPrinter implements Printer {

    private ResultsCalculator resultsCalculator;

    @Autowired
    public BasicPrinter(ResultsCalculator resultsCalculator) {
        this.resultsCalculator = resultsCalculator;
    }

    @Override
    public void print(List<Result> results) {
        System.out.println(resultsCalculator.calculateWinRatio(results) + "%");
    }
}
