package ru.kstu.aec.dao;

import java.util.List;
import java.util.Optional;

public interface StandartDAO<T> {

    Optional<T> show(long id);

    List<T> list();

    void save(T t);

    void update(T t);

    void delete(long id);
}
