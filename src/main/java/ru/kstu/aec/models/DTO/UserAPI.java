package ru.kstu.aec.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kstu.aec.models.DTO.CourseAPI;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAPI {

    private Long id;

    private String fullname;

    private int upr;
    private int pol;
    private int chl;

    private List<CourseAPI> courses = new ArrayList<>();
}
