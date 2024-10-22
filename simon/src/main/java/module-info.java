module fr.esgi {
    requires javafx.controls;
    requires javafx.fxml;

    opens fr.esgi.controller to javafx.fxml;
    exports fr.esgi;
}