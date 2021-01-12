package ro.mta.se.lab.model;

import javafx.scene.image.Image;
import org.json.JSONArray;
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

public class WeatherManager {
    private String city;
    private String day;
    private String icon;
    private String main;
    private long temperature;
    private long windSpeed;
    private long cloudiness;
    private long pressure;
    private long humidity;

    public WeatherManager(String city) {
        this.city = city;
    }

    //Build a String from the read Json file
    public void getWeather() throws IOException {
        StringBuilder resultJSON = new StringBuilder();
        String API_KEY = "1f13b5ccc40bdd6bacb7acedb6d9299c";
        String LOCATION = this.city;
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY;
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null)
            resultJSON.append(line);
        reader.close();
        JSONObject json = new JSONObject(resultJSON.toString());

        JSONArray weather = json.getJSONArray("weather");
        JSONObject main = json.getJSONObject("main");
        JSONObject wind = json.getJSONObject("wind");
        JSONObject clouds = json.getJSONObject("clouds");

        applySetters(weather,main,wind,clouds);

        Logger log = Logger.getInstance();
        log.write(json.toString(),"logFile.txt", this.city);
    }

    public void applySetters(JSONArray weather, JSONObject main, JSONObject wind, JSONObject clouds){
        setDay();
        setTemperature(main);
        setPressure(main);
        setHumidity(main);
        setWindSpeed(wind);
        setCloudiness(clouds);
        setIcon(weather);
        setMain(weather);
    }

    //Setters for all the private fields
    public void setDay(){
        DateFormat dateFormat = new SimpleDateFormat("EEEE hh:mm aa");
        Date date = new Date();
        this.day = dateFormat.format(date);
    }

    public void setTemperature(JSONObject main){
        this.temperature = Math.round(main.getDouble("temp") - 273.15);
    }

    public void setPressure(JSONObject main){
        this.pressure = main.getLong("pressure");
    }

    public void setHumidity(JSONObject main){
        this.humidity = main.getLong("humidity");
    }

    public void setWindSpeed(JSONObject wind){
        this.windSpeed = wind.getLong("speed");
    }

    public void setCloudiness(JSONObject clouds){
        this.cloudiness = clouds.getLong("all");
    }

    public void setIcon(JSONArray weather){
        File file = new File(ImageHandler.getImage(weather.getJSONObject(0).getString("icon")));
        this.icon = file.toURI().toString();
    }

    public void setMain(JSONArray weather){
        this.main = weather.getJSONObject(0).getString("description");
    }

    //Getters for all the private fields
    public String getCity() {
        return city;
    }

    public String getDay() {
        return day;
    }

    public long getTemperature() {
        return temperature;
    }

    public long getWindSpeed() {
        return windSpeed;
    }

    public long getCloudiness() {
        return cloudiness;
    }

    public long getPressure() {
        return pressure;
    }

    public long getHumidity() {
        return humidity;
    }

    public String getIcon() {
        return icon;
    }

    public String getMain(){
        return main;
    }
}
