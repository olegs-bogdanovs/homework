package com.dynatech.hometask.ob.reader.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDateTime localDateTime;

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metric metric = (Metric) o;
        return Objects.equals(id, metric.id) && Objects.equals(localDateTime, metric.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, localDateTime);
    }

    @Override
    public String toString() {
        return "Metric{" +
                "id=" + id +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
