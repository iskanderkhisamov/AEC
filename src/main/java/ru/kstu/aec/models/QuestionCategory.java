package ru.kstu.aec.models;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode
@Entity
@Table(name = "question_category", schema = "public", catalog = "omqkpgej")
public class QuestionCategory {
    private Long id;
    private String categoryName;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "category_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
