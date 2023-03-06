package jax.spring.microservices.guestservices;

import com.netflix.servo.publish.*;
import jax.spring.microservices.guestservices.servo.LoggerMetricObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.concurrent.TimeUnit;

public class ApplicationStartedListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Got event {}", contextRefreshedEvent.getSource());
        initMetricsPublishing();
    }

    private void initMetricsPublishing() {
        PollScheduler scheduler = PollScheduler.getInstance();
        if(!scheduler.isStarted()) {
            scheduler.start();
        }

        MetricObserver logObserver = new LoggerMetricObserver("logger-observer");

        MetricObserver logObserverRate = new LoggerMetricObserver("logger-observer-rate");
        MetricObserver transform = new CounterToRateMetricTransform(logObserverRate, 1, TimeUnit.MINUTES);
        PollRunnable task = new PollRunnable(
                new MonitorRegistryMetricPoller(),
                BasicMetricFilter.MATCH_ALL,
                logObserver, transform);
        scheduler.addPoller(task, 1, TimeUnit.MINUTES);
    }
}
