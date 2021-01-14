package ro.mta.se.lab.model;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static org.junit.Assert.*;

public class WeatherManagerTest {
    WeatherManager wManager;
    static String resultJSON;

    @BeforeClass
    static public void _init(){
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=Craiova&appid=1f13b5ccc40bdd6bacb7acedb6d9299c";
        {
            try {
                URL url = new URL(urlString);
                URLConnection conn = url.openConnection();
                StringBuilder res = new StringBuilder();
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null)
                    res.append(line);
                rd.close();
                resultJSON = res.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Before
    public void setUp() throws Exception {
        wManager = new WeatherManager(new WeatherModel.Entry("Craiova","Romania"));
        System.out.println("[] Before testing \n");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("[] After testing \n");
    }

    @Test
    public void createUrlString() {
        assertEquals(WeatherManager.createUrlString("1f13b5ccc40bdd6bacb7acedb6d9299c","Craiova"),"http://api.openweathermap.org/data/2.5/weather?q=" + wManager.getCity() + "&appid=1f13b5ccc40bdd6bacb7acedb6d9299c");
    }

    @Test
    public void getResultJSON() throws IOException {
        assertEquals(WeatherManager.getResultJSON("http://api.openweathermap.org/data/2.5/weather?q=Craiova&appid=1f13b5ccc40bdd6bacb7acedb6d9299c"),resultJSON);
    }
    //http://api.openweathermap.org/data/2.5/weather?q=Craiova&appid=1f13b5ccc40bdd6bacb7acedb6d9299c
    @Test
    public void getTemperature() {
        assertEquals(WeatherManager.getTemperature(),2);
    }

    @Test
    public void getMain() {
        assertEquals(WeatherManager.getMain(),"overcast clouds");
    }
}