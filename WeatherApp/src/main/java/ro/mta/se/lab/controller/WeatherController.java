package ro.mta.se.lab.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import ro.mta.se.lab.model.WeatherManager;
import ro.mta.se.lab.model.WeatherModel;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Class implementing the Controller part of the application
 * Implements functions that fill the lists of cities and countries
 * Also implements the function that shows weather results
 *
 * @author Panțucu Flavius-Marian
 */
public class WeatherController {
    /**
     * Member description
     */
    private WeatherManager wManager;
    private WeatherModel data;
    private String inputFile;
    /**
     * Controller class constructor
     * @param _infile Parameter tells us our start configuration
     */
    public WeatherController(String _infile){
        this.inputFile = _infile;
    }
    /**
     * FXML Member description
     */
    @FXML
    private ImageView myImg;
    @FXML
    private ChoiceBox myCountryBox, myCityBox;
    @FXML
    private Label myCityLabel, myTempLabel, myDescriptionLabel, dateLabel, myAditionalLabel;
    @FXML
    private Label myHumidityLabel, myCloudinessLabel, myWindSpeedLabel, myPressureLabel;
    /**
     * FXML initialization function
     */
    @FXML
    private void initialize() throws IOException {
        data = new WeatherModel(this.inputFile);
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
                    WeatherModel.Entry city = data.getCityEntry(newValue);
                    wManager = new WeatherManager(city);
                    showWeather();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * Function where we fill the FXML countryList to be shown to user.
     * @param _countryList Parameter list with the countries we gonna show
     */
    private void populateCountryList(ArrayList<StringProperty> _countryList){
        for (StringProperty country : _countryList)
            myCountryBox.getItems().addAll(country.get());
    }
    /**
     * Function where we fill the FXML countryList to be shown to user.
     * @param _cityList Parameter list with the cities we gonna show
     * @param _country Parameter name of the country to sort the list with cities.
     */
    private void populateCityList(ArrayList<WeatherModel.Entry> _cityList, String _country){
        myCityBox.getItems().clear();
        for (WeatherModel.Entry city : _cityList){
            if(city.getCountry().equals(_country))
                myCityBox.getItems().add(city.getCity());
        }
    }
    /**
     * Function that shows weather details on the interface.
     */
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
