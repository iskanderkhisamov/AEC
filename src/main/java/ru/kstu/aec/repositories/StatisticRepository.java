package ru.kstu.aec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kstu.aec.models.Statistic;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
}
