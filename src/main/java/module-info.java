module com.example.ud06herencia {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ud06herencia to javafx.fxml;
    exports com.example.ud06herencia;
}