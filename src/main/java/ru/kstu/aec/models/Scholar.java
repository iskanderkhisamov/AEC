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
    private Long userId;
    private Long courseId;
    private Long questionId;
    private int type;
    private int value;
}
