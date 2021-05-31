package ru.kstu.aec.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kstu.aec.models.Paragraph;
import ru.kstu.aec.models.Theme;
import ru.kstu.aec.repositories.ParagraphRepository;
import ru.kstu.aec.repositories.ThemeRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ParagraphService {
    private final ParagraphRepository paragraphRepository;

    public List<Paragraph> loadParagraphs() {
        return (List<Paragraph>) paragraphRepository.findAll();
    }
}
