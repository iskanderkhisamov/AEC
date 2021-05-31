package ru.kstu.aec.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@ToString
@NoArgsConstructor
@Setter
@Getter
public class TestForm {
    ArrayList<String> questions;
    ArrayList<String> answers;
}
