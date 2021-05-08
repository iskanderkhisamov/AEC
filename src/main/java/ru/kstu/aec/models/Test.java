package ru.kstu.aec.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "test", schema = "public", catalog = "omqkpgej")
public class Test {
    private long id;
    private String name;
    private String description;
    private Long paragraphId;
    private Double maxPolRating;
    private Double maxChlRating;
    private Double maxGlobalRating;

    @OneToMany(mappedBy = "tests")
    private Set<TestRating> testRatingSet;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "paragraph_id", nullable = true)
    public Long getParagraphId() {
        return paragraphId;
    }

    public void setParagraphId(Long paragraphId) {
        this.paragraphId = paragraphId;
    }

    @Basic
    @Column(name = "max_pol_rating", nullable = true, precision = 0)
    public Double getMaxPolRating() {
        return maxPolRating;
    }

    public void setMaxPolRating(Double maxPolRating) {
        this.maxPolRating = maxPolRating;
    }

    @Basic
    @Column(name = "max_chl_rating", nullable = true, precision = 0)
    public Double getMaxChlRating() {
        return maxChlRating;
    }

    public void setMaxChlRating(Double maxChlRating) {
        this.maxChlRating = maxChlRating;
    }

    @Basic
    @Column(name = "max_global_rating", nullable = true, precision = 0)
    public Double getMaxGlobalRating() {
        return maxGlobalRating;
    }

    public void setMaxGlobalRating(Double maxGlobalRating) {
        this.maxGlobalRating = maxGlobalRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test that = (Test) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (paragraphId != null ? !paragraphId.equals(that.paragraphId) : that.paragraphId != null) return false;
        if (maxPolRating != null ? !maxPolRating.equals(that.maxPolRating) : that.maxPolRating != null) return false;
        if (maxChlRating != null ? !maxChlRating.equals(that.maxChlRating) : that.maxChlRating != null) return false;
        if (maxGlobalRating != null ? !maxGlobalRating.equals(that.maxGlobalRating) : that.maxGlobalRating != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (paragraphId != null ? paragraphId.hashCode() : 0);
        result = 31 * result + (maxPolRating != null ? maxPolRating.hashCode() : 0);
        result = 31 * result + (maxChlRating != null ? maxChlRating.hashCode() : 0);
        result = 31 * result + (maxGlobalRating != null ? maxGlobalRating.hashCode() : 0);
        return result;
    }
}
