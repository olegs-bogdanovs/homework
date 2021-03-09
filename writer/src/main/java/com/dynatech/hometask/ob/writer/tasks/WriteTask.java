package com.dynatech.hometask.ob.writer.tasks;

import com.dynatech.hometask.ob.writer.domain.Metric;
import com.dynatech.hometask.ob.writer.repository.MetricRepository;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Histogram;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WriteTask {

    private MetricRepository metricRepository;
    private static final Logger log = LoggerFactory.getLogger(WriteTask.class);
    private final Histogram queryDuration;

    @Autowired
    public WriteTask(
            MetricRepository metricRepository,
            CollectorRegistry collectorRegistry
    ) {
        this.metricRepository = metricRepository;
        this.queryDuration = Histogram.build()
                .name("insert_query_duration")
                .help("Time for insert query")
                .register(collectorRegistry);
    }

    @Scheduled(fixedRate = 1000)
    public void writeMetric() {
        Metric metric = new Metric();
        metric.setLocalDateTime(LocalDateTime.now());

        Histogram.Timer timer = this.queryDuration.startTimer();
        Metric savedMetric = this.metricRepository.save(metric);
        timer.observeDuration();

        log.info("Object {} was written.", savedMetric);
    }

}
