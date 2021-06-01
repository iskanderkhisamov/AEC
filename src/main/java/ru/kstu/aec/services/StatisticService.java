package ru.kstu.aec.services;

import org.springframework.stereotype.Service;
import ru.kstu.aec.models.Statistic;
import ru.kstu.aec.repositories.StatisticRepository;

import java.util.Optional;

@Service
public class StatisticService {

    final StatisticRepository statisticRepository;

    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public Statistic getStatistic(Long id) throws Exception {
        Optional<Statistic> statistic = statisticRepository.findById(id);
        return statistic.orElseThrow(() -> new Exception("Такой статистики нет, id = " + id));
    }

    public void saveStatistic(Statistic statistic) {
        statisticRepository.save(statistic);
    }
}
