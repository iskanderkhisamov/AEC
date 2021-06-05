package ru.kstu.aec.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kstu.aec.models.Statistic;
import ru.kstu.aec.models.Test;
import ru.kstu.aec.repositories.StatisticRepository;

@Service
public class StatisticService {

    final StatisticRepository statisticRepository;

    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public Statistic getStatistic() throws Exception {
        Statistic last = null;
        for (Statistic st : statisticRepository.findAll()) {
            last = st;
        }
        return last;
    }

    @Transactional
    public void saveStatistic(Statistic statistic) {
        statisticRepository.save(statistic);
    }

    @Transactional
    public void deleteStatistic(Statistic statistic) {
        statisticRepository.delete(statistic);
    }
}
