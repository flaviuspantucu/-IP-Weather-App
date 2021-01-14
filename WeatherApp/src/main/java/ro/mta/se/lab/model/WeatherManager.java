package ro.mta.se.lab.model;

import org.json.JSONObject;
import ro.mta.se.lab.view.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Class implementing the Model of the City weather details and JSON respond from api url
 * Implements functions parse the JSON
 *
 * @author Panțucu Flavius-Marian
 */
public class WeatherManager {
    /**
     * Member description
     */
    private static WeatherInfo City;
    private static JSONObject weatherJSON;
    /**
     * WeatherManager class constructor
     * @param _city Parameter stores the city entry
     */
    public WeatherManager(WeatherModel.Entry _city) throws IOException {
        String resultJSON = getResultJSON(createUrlString("1f13b5ccc40bdd6bacb7acedb6d9299c",_city.getCity()));
        weatherJSON = new JSONObject(resultJSON);
        getWeather(_city);
    }
    /**
     * Function that returns url for api
     * @param API_KEY Parameter that stores api key
     * @param LOCATION Parameter that store city name
     */
    public static String createUrlString(String API_KEY, String LOCATION){
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY;
        return url;
    }
    /**
     * Function that returns jsonResult
     * @param URL_CONNECTION Parameter that url used for getting json results
     */
    public static String getResultJSON(String URL_CONNECTION) throws IOException {
        URL url = new URL(URL_CONNECTION);
        URLConnection CONNECTION = url.openConnection();
        StringBuilder resultJSON = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(CONNECTION.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null)
            resultJSON.append(line);
        reader.close();
        return resultJSON.toString();
    }
    /**
     * Function that get all weather details
     * @param _city Parameter that url used for getting json parse results
     */
    public void getWeather(WeatherModel.Entry _city) throws IOException {
        String _day = getJSONDay();
        String _icon = getJSONIcon();
        String _main = getJSONMain();
        long _temperature = getJSONTemp();
        long _windSpeed = getJSONWind();
        long _cloudiness = getJSONCloud();
        long _pressure = getJSONPressure();
        long _humidity = getJSONHumidity();

        City = new WeatherInfo(_city, _day,_icon,_main,_temperature,_windSpeed,_cloudiness,_pressure,_humidity);

        Logger log = Logger.getInstance();
        log.write(weatherJSON.toString(),"logFile.txt", _city.getCity().toString());
    }
    /**
     * Functions that parse the json to return the field we want
     */
    public long getJSONHumidity() {
        return weatherJSON.getJSONObject("main").getLong("humidity");
    }

    public long getJSONPressure() {
        return weatherJSON.getJSONObject("main").getLong("pressure");
    }

    public long getJSONCloud() {
        return weatherJSON.getJSONObject("clouds").getLong("all");
    }

    public long getJSONWind() {
        return weatherJSON.getJSONObject("wind").getLong("speed");
    }

    public long getJSONTemp() {
        return Math.round(weatherJSON.getJSONObject("main").getDouble("temp") - 273.15);
    }

    public String getJSONMain() {
        return weatherJSON.getJSONArray("weather").getJSONObject(0).getString("description");
    }

    public String getJSONDay(){
        DateFormat dateFormat = new SimpleDateFormat("EEEE hh:mm aa");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getJSONIcon(){
        ImageHandler img = new ImageHandler();
        File file = new File(img.getImage(weatherJSON.getJSONArray("weather").getJSONObject(0).getString("icon")));
        return file.toURI().toString();
    }
    /**
     * Getters for private fields
     */
    public String getCity() {
        return City.getCity();
    }

    public String getDay() {
        return City.getDay();
    }

    public static long getTemperature() {
        return City.getTemperature();
    }

    public long getWindSpeed() {
        return City.getWindSpeed();
    }

    public long getCloudiness() {
        return City.getCloudiness();
    }

    public long getPressure() {
        return City.getPressure();
    }

    public long getHumidity() {
        return City.getHumidity();
    }

    public String getIcon() {
        return City.getIcon();
    }

    public static String getMain(){
        return City.getMain();
    }

    public String getCountry() { return City.getCountry(); }
}
