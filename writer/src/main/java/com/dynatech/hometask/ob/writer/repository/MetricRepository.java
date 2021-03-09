package com.dynatech.hometask.ob.writer.repository;

import com.dynatech.hometask.ob.writer.domain.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends JpaRepository<Metric, Long> {
}
