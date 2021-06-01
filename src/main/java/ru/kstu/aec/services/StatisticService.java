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

    public Statistic getStatistic() throws Exception {
        Statistic last = null;
        for(Statistic st : statisticRepository.findAll()) {
            last = st;
        }
        return last;
    }

    public void saveStatistic(Statistic statistic) {
        statisticRepository.save(statistic);
    }
}
