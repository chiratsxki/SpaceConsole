module com.chiratsxki.spaceconsole {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;


    opens com.chiratsxki.spaceconsole to javafx.fxml;
    exports com.chiratsxki.spaceconsole;
}