module com.example.collision {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.collision to javafx.fxml;
    exports com.example.collision;
}