module co.edu.uniquindio.agenciacliente {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires javafx.graphics;
    requires static lombok;

    opens co.edu.uniquindio.agenciacliente.controllers to javafx.fxml;

    exports co.edu.uniquindio.agenciacliente.app;
    exports co.edu.uniquindio.agenciacliente.socket;
    exports co.edu.uniquindio.agenciacliente.model;
    exports co.edu.uniquindio.agenciacliente.datos;
    exports co.edu.uniquindio.agenciacliente.controllers;
    exports co.edu.uniquindio.agenciacliente.exceptions;
    exports co.edu.uniquindio.agenciacliente.enums;
}