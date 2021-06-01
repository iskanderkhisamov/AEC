package ru.kstu.aec.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO {

    private Long id;

    private List<Question> questions = new ArrayList<>();

}
