package co.edu.uniquindio.agenciacliente.model;

import co.edu.uniquindio.agenciacliente.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor @Builder

public class Reservation implements Serializable {

    private TouristPackage touristPackage;
    private LocalDate requestDate;
    private ReservationStatus reservationStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private TouristGuide touristGuide;
    private Integer numberOfPeople;

}
