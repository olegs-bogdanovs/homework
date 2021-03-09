package com.dynatech.hometask.ob.reader.repository;

import com.dynatech.hometask.ob.reader.domain.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends JpaRepository<Metric, Long> {
}
