package ro.mta.se.lab.model;
/**
 * Class implementing the Model of the weather info
 *
 * @author Panțucu Flavius-Marian
 */
public class WeatherInfo {
    /**
     * Member description
     */
    private static WeatherModel.Entry city;
    private static String day;
    private static String icon;
    private static String main;
    private static long temperature;
    private static long windSpeed;
    private static long cloudiness;
    private static long pressure;
    private static long humidity;
    /**
     * WeatherInfo class constructor
     * @param _city Parameter stores the city entry
     * @param _day Parameter stores the day value
     * @param _icon Parameter stores the icon name
     * @param _main Parameter stores the main value
     * @param _temperature Parameter stores the temperature value
     * @param _windSpeed Parameter stores the wind speed value
     * @param _cloudiness Parameter stores the cloudiness value
     * @param _pressure Parameter stores the pressure value
     * @param _humidity Parameter stores the humidity value
     *
     */
    public WeatherInfo(WeatherModel.Entry _city, String _day, String _icon, String _main, long _temperature, long _windSpeed, long _cloudiness, long _pressure, long _humidity){
        setCity(_city);
        setDay(_day);
        setIcon(_icon);
        setMain(_main);
        setHumidity(_humidity);
        setPressure(_pressure);
        setWindSpeed(_windSpeed);
        setCloudiness(_cloudiness);
        setTemperature(_temperature);
    }
    /**
     * Setters for private fields
     */
    public void setCity(WeatherModel.Entry _city){ city = new WeatherModel.Entry(_city.getCity().toString(),_city.getCountry().toString());}

    public void setDay(String _day){
        day = _day;
    }

    public void setTemperature(long _temperature){
        temperature = _temperature;
    }

    public void setPressure(long _pressure){
        pressure = _pressure;
    }

    public void setHumidity(long _humidity){
        humidity = _humidity;
    }

    public void setWindSpeed(long _windSpeed){
        windSpeed = _windSpeed;
    }

    public void setCloudiness(long _cloudiness){
        cloudiness = _cloudiness;
    }

    public void setIcon(String _icon){
        icon = _icon;
    }

    public void setMain(String _main){
        main = _main;
    }
    /**
     * Getters for private fields
     */
    public String getCity() {
        return city.getCity();
    }

    public String getCountry() { return city.getCountry();}

    public String getDay() {
        return day;
    }

    public static long getTemperature() {
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

    public static String getMain(){
        return main;
    }
}
