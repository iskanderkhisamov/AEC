package ru.kstu.aec.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Scholar {
    private Long id;
    private String name;
    private int type;
    private int value;
    private int third;
    // недоделанный класс для студента, является чем-то типа DTO для удобства и передачи инфы в фронт
}
