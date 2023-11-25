module com.example.serpientesyescaleras {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.serpientesyescaleras to javafx.fxml;
    exports com.example.serpientesyescaleras;
    exports com.example.serpientesyescaleras.control;
    opens com.example.serpientesyescaleras.control to javafx.fxml;
}