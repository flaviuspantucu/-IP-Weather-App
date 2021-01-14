package ro.mta.se.lab.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MockTest {

    private WeatherManager weatherInstance;
    private WeatherModel.Entry cityInstance;

    @Before
    public void setUp() throws IOException {
        cityInstance = mock(WeatherModel.Entry.class);

        when(cityInstance.getCity()).thenReturn("Bucharest");
        when(cityInstance.getCountry()).thenReturn("Bulgaria");

        weatherInstance = new WeatherManager(cityInstance);
    }

    @Test
    public void test(){
        assertEquals("Bucharest",weatherInstance.getCity());
        assertEquals("Bulgaria",weatherInstance.getCountry());
    }
}
