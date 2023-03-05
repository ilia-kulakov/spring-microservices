package jax.spring.microservices.roomreservationservice;
import java.sql.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("reservationservices")
public interface ReservationClient {
    @GetMapping("/reservations")
    List<Reservation> getAllReservations(@RequestParam(name="date", required = false) Date date);

    @GetMapping("/reservations/{id}")
    Reservation getReservation(@PathVariable("id")long id);
}
