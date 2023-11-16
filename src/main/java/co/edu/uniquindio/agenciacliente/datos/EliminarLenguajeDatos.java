package co.edu.uniquindio.agenciacliente.datos;

import co.edu.uniquindio.agenciacliente.model.TouristGuide;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Optional;
@Getter
@Setter
@AllArgsConstructor
@Builder
public class EliminarLenguajeDatos implements Serializable {
    private Optional<TouristGuide> guideSeleccionadoOpcional;
    private String selectedLenguaje;
}
