package ro.mta.se.lab.model;

public class ImageHandler {
    public static String getImage(String icon) {
        switch (icon){
            case "01d":
                return "src/main/resources/images/01d.png";
            case "01n":
                return "src/main/resources/images/01n.png";
            case "02d":
                return "src/main/resources/images/02d.png";
            case "02n":
                return "src/main/resources/images/02n.png";
            case "03d": case "03n":
                return "src/main/resources/images/03.png";
            case "04d": case "04n":
                return "src/main/resources/images/04.png";
            case "09d": case "09n":
                return "src/main/resources/images/09.png";
            case "10d":
                return "src/main/resources/images/10d.png";
            case "10n":
                return "src/main/resources/images/10n.png";
            case "11n": case "11d":
                return "src/main/resources/images/11.png";
            case "13d": case "13n":
                return "src/main/resources/images/13.png";
            case "50d": case "50n":
                return "src/main/resources/images/50.png";
        }
        return "src/main/resources/images/01d.jpg";
    }
}
