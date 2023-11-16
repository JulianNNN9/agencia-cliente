package co.edu.uniquindio.agenciacliente.datos;

import co.edu.uniquindio.agenciacliente.model.Destino;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AgregarImagenDestinoDatos implements Serializable {
    private String ruta;
    private Destino destino;
}
