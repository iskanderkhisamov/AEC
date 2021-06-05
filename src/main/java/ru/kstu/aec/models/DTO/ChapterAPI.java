package ru.kstu.aec.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChapterAPI {

    private Long id;

    private String name;

    private int upr;
    private int pol;
    private int chl;

    private List<TestAPI> tests = new ArrayList<>();

    private Long course;
}
