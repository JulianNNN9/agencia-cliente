module co.edu.uniquindio.agenciacliente {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.agenciacliente to javafx.fxml;
    exports co.edu.uniquindio.agenciacliente;
}