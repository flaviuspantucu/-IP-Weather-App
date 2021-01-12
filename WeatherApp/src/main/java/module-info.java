module ro.mta.se.lab {
    requires javafx.fxml;
    requires javafx.controls;
    requires org.json;
    opens ro.mta.se.lab.controller to javafx.fxml;
    exports ro.mta.se.lab;
}