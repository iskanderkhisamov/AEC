package ru.kstu.aec.models;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

    private Long test_id;

    private Set<Question> questions = new HashSet<>();

}
