package Adapter_Des_Patt;





import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class WeatherData {
    private String location;
    private double temperature;
    private double humidity;

    public WeatherData(String location, double temperature, double humidity) {
        this.location = location;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String getLocation() {
        return location;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }
}

class OpenWeatherAdapter {
    private static final String API_KEY = "8253329960c939e751274ac66658e55a\n";

    public WeatherData fetchWeatherData(String location) {
        try {
            String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + API_KEY;
            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());

            String cityName = jsonResponse.getString("name");
            JSONObject main = jsonResponse.getJSONObject("main");
            double temperature = main.getDouble("temp");
            double humidity = main.getDouble("humidity");

            temperature -= 273.15;

            return new WeatherData(cityName, temperature, humidity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

class AccuWeatherAdapter {
    private static final String API_KEY = "5hayZkamjlVg21BJI0o6LTUsRhf6Wv9P\n";

    public WeatherData getWeather(String location) {
        try {
            String apiUrl = "http://dataservice.accuweather.com/currentconditions/v1/" + location + "?apikey=" + API_KEY;
            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());

            String cityName = jsonResponse.getJSONObject("Location").getString("LocalizedName");
            double temperature = jsonResponse.getDouble("Temperature");
            double humidity = jsonResponse.getDouble("RelativeHumidity");

            return new WeatherData(cityName, temperature, humidity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

public class Main_3 {
    public static void main(String[] args) {
        OpenWeatherAdapter openWeatherAdapter = new OpenWeatherAdapter();
        WeatherData openWeatherData = openWeatherAdapter.fetchWeatherData("New York");

        AccuWeatherAdapter accuWeatherAdapter = new AccuWeatherAdapter();
        WeatherData accuWeatherData = accuWeatherAdapter.getWeather("London");

        System.out.println("Weather in New York:");
        System.out.println("Temperature: " + openWeatherData.getTemperature() + "°C");
        System.out.println("Humidity: " + openWeatherData.getHumidity() + "%");

        System.out.println("\nWeather in London:");
        System.out.println("Temperature: " + accuWeatherData.getTemperature() + "°C");
        System.out.println("Humidity: " + accuWeatherData.getHumidity() + "%");
    }
}
