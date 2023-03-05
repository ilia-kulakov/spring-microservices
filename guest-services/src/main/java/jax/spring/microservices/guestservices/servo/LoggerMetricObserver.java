package jax.spring.microservices.guestservices.servo;

import com.netflix.servo.Metric;
import com.netflix.servo.publish.BaseMetricObserver;
import com.netflix.servo.util.Preconditions;
import com.netflix.servo.util.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.joda.time.LocalDateTime;
import java.util.List;

public class LoggerMetricObserver extends BaseMetricObserver {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public LoggerMetricObserver(String name) {
        super(name);
    }

    @Override
    public void updateImpl(List<Metric> metrics) {
        Preconditions.checkNotNull(metrics, "metrics");
        try {
            for (Metric m : metrics) {
                logger.info("{}: name[{}] tags[{}] value[{}]",
                        new LocalDateTime(m.getTimestamp()),
                        m.getConfig().getName(),
                        m.getConfig().getTags(),
                        m.getValue());
            }
        } catch (Throwable t) {
            incrementFailedCount();
            throw Throwables.propagate(t);
        }
    }
}
