package ru.kstu.aec.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestAPI {

    private Long id;

    private String name;

    private int upr;
    private int pol;
    private int chl;

    private Long chapter;
}
