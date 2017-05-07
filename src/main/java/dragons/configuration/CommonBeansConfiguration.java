package dragons.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dragons.engine.calculators.ResultsCalculator;
import dragons.engine.printers.BasicPrinter;
import dragons.engine.printers.DetailedPrinter;
import dragons.engine.printers.Printer;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:application.properties")
public class CommonBeansConfiguration {

    @Value("${game.printer.type}")
    private String printerType;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClients.createDefault();
    }

    @Bean
    public Printer printer() {
        switch (printerType) {
            case "detailed":
                return new DetailedPrinter(resultsCalculator());
            case "basic":
                return new BasicPrinter(resultsCalculator());
            default:
                return new BasicPrinter(resultsCalculator());
        }
    }

    @Bean
    public ResultsCalculator resultsCalculator() {
        return new ResultsCalculator();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
