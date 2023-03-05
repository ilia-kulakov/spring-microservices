package jax.spring.microservices.reservationservices;

import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/reservations")
public class ReservationWebServices {
    private ReservationRepository repository;

    public ReservationWebServices(ReservationRepository repository){
        super();
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Reservation> getReservations(@RequestParam(name = "date", required = false) Date date) {
        return (null != date) ? this.repository.findAllByDate(date) : this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable("id") long id){
        return this.repository.findById(id).get();
    }
}
