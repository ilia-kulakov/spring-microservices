package jax.spring.microservices.feignclient;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "guestservices", url = "${balance.server.uri}/guestservices")
public interface GuestClient {

    @GetMapping("/guests")
    List<Guest> getAllGuests();

    @GetMapping("/guests/{id}")
    Guest getGuest(@PathVariable("id")long id);
}
