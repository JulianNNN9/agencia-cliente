package co.edu.uniquindio.agenciacliente.datos;

import co.edu.uniquindio.agenciacliente.model.TouristGuide;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class CalificarGuiaDatos implements Serializable {

    private TouristGuide touristGuide;
    private String estrellas;
}
