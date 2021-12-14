import org.json.simple.parser.ParseException;

import java.io.IOException;

public class TestRun {

    public static void main(String[] args) throws IOException, ParseException {
        new GetWeather().getWeatherFromAPi();
    }
}
