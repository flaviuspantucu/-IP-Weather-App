package ro.mta.se.lab.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WeatherModel {
    public static class Entry{
        StringProperty city;
        StringProperty country;

        public Entry(String _city, String _country){
            this.city = new SimpleStringProperty(_city);
            this.country = new SimpleStringProperty(_country);
        }
        public StringProperty getCountry(){
            return this.country;
        }
        public StringProperty getCity(){
            return this.city;
        }
    }

    private ArrayList<StringProperty> countryList;
    private ArrayList<Entry> cityList;

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

    public void setCountryList(ArrayList<StringProperty> _countryList){
        this.countryList = new ArrayList<>(_countryList);
    }

    public void setCityList(ArrayList<Entry> _cityList){
        this.cityList = new ArrayList<>(_cityList);
    }

    public ArrayList<Entry> getCityList(){
        return this.cityList;
    }

    public ArrayList<StringProperty> getCountryList(){
        return this.countryList;
    }
}
