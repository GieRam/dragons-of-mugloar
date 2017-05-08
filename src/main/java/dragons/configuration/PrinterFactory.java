package dragons.configuration;

import dragons.engine.calculators.ResultsCalculator;
import dragons.engine.printers.BasicPrinter;
import dragons.engine.printers.DetailedPrinter;
import dragons.engine.printers.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PrinterFactory {

    private String printerType;
    private ResultsCalculator resultsCalculator;

    @Autowired
    public PrinterFactory(@Value("${printer.type}") String printerType,
                          ResultsCalculator resultsCalculator) {
        this.printerType = printerType;
        this.resultsCalculator = resultsCalculator;
    }

    public Printer createPrinter() {
        switch (printerType) {
            case "detailed":
                return new DetailedPrinter(resultsCalculator);
            case "basic":
                return new BasicPrinter(resultsCalculator);
            default:
                return new BasicPrinter(resultsCalculator);
        }
    }
}
