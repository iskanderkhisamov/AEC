package ru.kstu.aec.models;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode
@Entity
@Table(name = "question_type", schema = "public", catalog = "omqkpgej")
public class QuestionTypeEntity {
    private Long id;
    private String typeName;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type_name")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
