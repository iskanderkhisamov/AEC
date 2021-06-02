package ru.kstu.aec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kstu.aec.models.Statistic;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {
}
