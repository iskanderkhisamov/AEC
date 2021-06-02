package ru.kstu.aec.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO {

    private Long id;

    private Set<QuestionDTO> questions = new HashSet<>();

}
