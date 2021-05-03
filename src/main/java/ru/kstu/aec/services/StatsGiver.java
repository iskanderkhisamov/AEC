package ru.kstu.aec.services;

import org.springframework.stereotype.Service;
import ru.kstu.aec.models.Scholar;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatsGiver {
    private static List<Scholar> scholarList = new ArrayList<>();

    static {
        scholarList.add(new Scholar(1L, "tupak", 5, 3, 7));
    }
    public Scholar findByName(String name) {
        for(int i = 0; i < scholarList.size(); i++) {
            if(scholarList.get(i).getName().equals(name)) {
                return scholarList.get(i);
            }
        }
        return null;
    }
    public List<Scholar> findAll() {
        return scholarList;
    }
    // раскомментить, когда придёт время
}
