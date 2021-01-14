package ro.mta.se.lab.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 * Class implementing the Model of the lists part of the application
 * Implements functions that fill the lists of cities and countries
 *
 * @author Panțucu Flavius-Marian
 */
public class WeatherModel {
    /**
     * Class used to remember city details
     *
     */
    public static class Entry{
        /**
         * Member description
         */
        private String city;
        private String country;
        /**
         * Entry class constructor
         * @param _city Parameter stores the city name
         * @param _country Parameter stores the country name
         */
        public Entry(String _city, String _country){
            this.city = _city;
            this.country = _country;
        }
        /**
         * Getters for private fields
         *
         */
        public String getCountry(){
            return this.country;
        }
        public String getCity(){
            return this.city;
        }
    }
    /**
     * Member description
     */
    private ArrayList<StringProperty> countryList;
    private ArrayList<Entry> cityList;
    /**
     * WeatherModel class constructor
     * @param inputFile Parameter filename that stores the configuration
     *
     */
    public WeatherModel(String inputFile) throws IOException {
        this.countryList = new ArrayList<>();
        this.cityList = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(inputFile));
        for (String line : lines) {
            String[] aux = line.split(" ", 0);
            cityList.add(new WeatherModel.Entry(aux[1], aux[0]));
            int addedCountry = 0;
            for (StringProperty country : countryList) {
                if (country.get().equals(aux[0]))
                    addedCountry = 1;
            }
            if (addedCountry == 0)
                countryList.add(new SimpleStringProperty(aux[0]));
        }
    }
    /**
     * Function that returns city Entry
     * @param _city Parameter that stores city name
     * @return an Entry of a city
     */
    public Entry getCityEntry(String _city){
        for( Entry city : cityList) {
            if (city.getCity().toString() == _city)
                return city;
        }
        return null;
    }
    /**
     * WeatherModel getters for private fields
     *
     */
    public ArrayList<Entry> getCityList(){
        return this.cityList;
    }

    public ArrayList<StringProperty> getCountryList(){
        return this.countryList;
    }
}
