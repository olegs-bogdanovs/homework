package com.dynatech.hometask.ob.reader.tasks;


import com.dynatech.hometask.ob.reader.repository.MetricRepository;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Histogram;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ReadTask {

    private MetricRepository metricRepository;
    private static final Logger log = LoggerFactory.getLogger(ReadTask.class);
    private final Histogram queryDuration;

    @Autowired
    public ReadTask(
            MetricRepository metricRepository,
            CollectorRegistry collectorRegistry
    ) {
        this.metricRepository = metricRepository;
        this.queryDuration = Histogram.build()
                .name("count_query_duration")
                .help("Time for count query")
                .register(collectorRegistry);
    }

    @Scheduled(fixedRate = 1000)
    public void writeMetric() {

        Histogram.Timer timer = this.queryDuration.startTimer();
        Long count = this.metricRepository.count();
        timer.observeDuration();

        log.info("Database contains {} records", count);
    }

}
