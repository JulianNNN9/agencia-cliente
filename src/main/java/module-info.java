module co.edu.uniquindio.agenciacliente {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires javafx.graphics;
    requires static lombok;

    opens co.edu.uniquindio.ingesis.controllers to javafx.fxml;

    exports co.edu.uniquindio.ingesis.app;
    exports co.edu.uniquindio.ingesis.socket;
    exports co.edu.uniquindio.ingesis.model;
    exports co.edu.uniquindio.ingesis.datos;
    exports co.edu.uniquindio.ingesis.controllers;
    exports co.edu.uniquindio.ingesis.exceptions;
    exports co.edu.uniquindio.ingesis.enums;
}