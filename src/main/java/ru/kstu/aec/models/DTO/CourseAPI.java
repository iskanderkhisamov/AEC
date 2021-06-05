package ru.kstu.aec.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kstu.aec.models.DTO.ChapterAPI;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseAPI {

    private Long id;

    private String name;

    private int upr;
    private int pol;
    private int chl;

    private List<ChapterAPI> chapters = new ArrayList<>();

    private Long user;
}
