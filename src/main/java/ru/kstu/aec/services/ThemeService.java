package ru.kstu.aec.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kstu.aec.models.Course;
import ru.kstu.aec.models.Specialization;
import ru.kstu.aec.models.Theme;
import ru.kstu.aec.repositories.SpecializationRepository;
import ru.kstu.aec.repositories.ThemeRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    public List<Theme> loadThemes() {
        return (List<Theme>) themeRepository.findAll();
    }

    public Theme getTheme(Long id) {
        return themeRepository.getOne(id);
    }

    public List<Theme> getThemeByCourse(Long id) {
        return themeRepository.getByCourse(id);
    }
}
