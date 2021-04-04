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
    private int stat;
    private int sta;
    private int st;
}
