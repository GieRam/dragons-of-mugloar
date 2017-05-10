package dragons.configuration

import dragons.engine.calculators.ResultsCalculator
import dragons.engine.printers.BasicPrinter
import dragons.engine.printers.DetailedPrinter
import spock.lang.Specification

class PrinterFactorySpec extends Specification {

    def 'should create detailed printer'() {
        given:
            def factory = createPrinterFactory('detailed')
        when:
            def printer = factory.createPrinter()
        then:
            printer in DetailedPrinter
    }

    def 'should create basic printer'() {
        given:
            def factory = createPrinterFactory('basic')
        when:
            def printer = factory.createPrinter()
        then:
            printer in BasicPrinter
    }

    def 'should create basic printer as default'() {
        given:
            def factory = createPrinterFactory('')
        when:
            def printer = factory.createPrinter()
        then:
            printer in BasicPrinter
    }

    PrinterFactory createPrinterFactory(String printerType) {
        return new PrinterFactory(printerType, new ResultsCalculator())
    }
}
