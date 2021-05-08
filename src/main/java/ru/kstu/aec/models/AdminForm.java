package ru.kstu.aec.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@NoArgsConstructor
@Setter
@Getter
public class AdminForm {
    List<User> users;
}

