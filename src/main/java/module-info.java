module com.example.javasudokugame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javasudokugame to javafx.fxml;
    exports com.example.javasudokugame;
    exports com.example.javasudokugame.model;
    opens com.example.javasudokugame.model to javafx.fxml;
    exports com.example.javasudokugame.interfaces;
    opens com.example.javasudokugame.interfaces to javafx.fxml;
    exports com.example.javasudokugame.controller;
    opens com.example.javasudokugame.controller to javafx.fxml;
    exports com.example.javasudokugame.view;
    opens com.example.javasudokugame.view to javafx.fxml;
}