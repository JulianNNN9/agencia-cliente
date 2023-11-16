package co.edu.uniquindio.agenciacliente.datos;

import co.edu.uniquindio.agenciacliente.model.Destino;
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
public class EliminarRutaDatos implements Serializable {
    private Optional<Destino> destinoSeleccionadoOpcional;
    private String selectedRuta;
}
