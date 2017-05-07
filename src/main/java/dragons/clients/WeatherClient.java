package dragons.clients;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dragons.entities.weather.WeatherReport;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeatherClient extends AbstractClient {

    private HttpClient httpClient;
    private String weatherUrl;
    private XmlMapper xmlMapper;

    @Autowired
    public WeatherClient(HttpClient httpClient,
                         @Value("${dragons.weather.url}") String weatherUrl) {
        this.httpClient = httpClient;
        this.weatherUrl = weatherUrl;
        this.xmlMapper = new XmlMapper();
    }

    public WeatherReport getWeatherReport(Integer gameId) throws Exception {
        HttpResponse response = managedRequest(() -> {
            HttpGet request = new HttpGet(weatherUrl + gameId);
            return httpClient.execute(request);
        });
        return xmlMapper.readValue(response.getEntity().getContent(), WeatherReport.class);
    }
}
