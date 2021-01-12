package ro.mta.se.lab.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import ro.mta.se.lab.model.WeatherManager;
import ro.mta.se.lab.model.WeatherModel;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WeatherController {
    private WeatherManager wManager;
    private WeatherModel data;
    private String inputFile;

    public WeatherController(String _infile){
        this.inputFile = _infile;
    }

    @FXML
    private ImageView myImg;
    @FXML
    private ChoiceBox myCountryBox, myCityBox;
    @FXML
    private Label myCityLabel, myTempLabel, myDescriptionLabel, dateLabel, myAditionalLabel;
    @FXML
    private Label myHumidityLabel, myCloudinessLabel, myWindSpeedLabel, myPressureLabel;

    @FXML
    private void initialize() throws IOException {
        this.data = new WeatherModel(this.inputFile);
        populateCountryList(data.getCountryList());

        myCountryBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                populateCityList(data.getCityList(),newValue);
            }
        });

        myCityBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    wManager = new WeatherManager(newValue);
                    wManager.getWeather();
                    showWeather();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void populateCountryList(ArrayList<StringProperty> _countryList){
        for (StringProperty country : _countryList)
            myCountryBox.getItems().addAll(country.get());
    }

    private void populateCityList(ArrayList<WeatherModel.Entry> _cityList, String _country){
        myCityBox.getItems().clear();
        for (WeatherModel.Entry city : _cityList){
            if(city.getCountry().get().equals(_country))
                myCityBox.getItems().add(city.getCity().get());
        }
    }

    private void showWeather() throws IOException {
        myDescriptionLabel.setText(wManager.getMain());
        myCityLabel.setText(wManager.getCity());
        dateLabel.setText(wManager.getDay());
        myTempLabel.setText(String.valueOf(wManager.getTemperature()) + "°C");
        myPressureLabel.setText("Pressure: " + String.valueOf(wManager.getPressure()) + " hpa");
        myHumidityLabel.setText("Humidity: " + String.valueOf(wManager.getHumidity()) + "%");
        myAditionalLabel.setText("Aditional Information");
        myWindSpeedLabel.setText("Wind speed: " + String.valueOf(wManager.getWindSpeed()) + " km/h");
        myCloudinessLabel.setText("Cloudiness: " + String.valueOf(wManager.getCloudiness()) + "%");
        myImg.setImage(new Image(wManager.getIcon()));
    }
}
