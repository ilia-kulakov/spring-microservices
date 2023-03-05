package jax.spring.microservices.guestservices;

import com.netflix.discovery.EurekaClient;
import com.netflix.servo.annotations.DataSourceLevel;
import com.netflix.servo.annotations.DataSourceType;
import com.netflix.servo.annotations.Monitor;
import com.netflix.servo.annotations.MonitorTags;
import com.netflix.servo.monitor.Monitors;
import com.netflix.servo.tag.BasicTagList;
import com.netflix.servo.tag.TagList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/guests")
public class GuestController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private final GuestRepository guestRepository;

    @Monitor(
            name = "requestCounter",
            type = DataSourceType.INFORMATIONAL,
            description = "Total number of requests",
            level = DataSourceLevel.CRITICAL)
    private final AtomicLong requestCounter = new AtomicLong(0);

    @Monitor(
            name = "guestGauge",
            type = DataSourceType.GAUGE,
            description = "A gauge of guest count",
            level = DataSourceLevel.CRITICAL)
    private final AtomicLong guestGauge = new AtomicLong(0);

    @MonitorTags
    private final TagList tags = BasicTagList.of(
            "id", "guestController",
            "class", "jax.spring.microservices.guestservices.GuestController");

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private String portNumber;

    public GuestController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @PostConstruct
    public void init() {
        Monitors.registerObject("guestController", this);
    }

    @GetMapping("/greeting")
    String greeting() {
        requestCounter.incrementAndGet();
        String greeting = String.format("Hello from '%s with Port Number %s'! Check logs to identify the responder.",
                eurekaClient.getApplication(appName).getName(), portNumber);
        logger.info(greeting);
        return greeting;
    }

    @GetMapping
    Iterable<Guest> getAllGuest() {
        requestCounter.incrementAndGet();
        guestGauge.set(this.guestRepository.count());
        return this.guestRepository.findAll();
    }

    @GetMapping("/{id}")
    public Guest getGuest(@PathVariable("id") long id) {
        requestCounter.incrementAndGet();
        return this.guestRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Con not find guest with id " + id));
    }
}
