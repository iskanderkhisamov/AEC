package ru.kstu.aec.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kstu.aec.models.Specialization;
import ru.kstu.aec.repositories.SpecializationRepository;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class SpecializationService {
    private final SpecializationRepository specializationRepository;

    public List<Specialization> loadSpecializations() {
        return (List<Specialization>) specializationRepository.findAll();
    }

    @Transactional
    public void createSpecialization(Specialization specialization) {
        specializationRepository.save(specialization);
    }

    public String getSpecialization(Long id) {
        return specializationRepository.getName(id);
    }
}
