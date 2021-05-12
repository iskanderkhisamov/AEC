package ru.kstu.aec.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "test_rating", schema = "public", catalog = "omqkpgej")
public class TestRating {
    private long id;
    private Double polScore;
    private Double chlScore;
    private Double globalScore;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User user;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pol_score", nullable = true, precision = 0)
    public Double getPolScore() {
        return polScore;
    }

    public void setPolScore(Double polScore) {
        this.polScore = polScore;
    }

    @Basic
    @Column(name = "chl_score", nullable = true, precision = 0)
    public Double getChlScore() {
        return chlScore;
    }

    public void setChlScore(Double chlScore) {
        this.chlScore = chlScore;
    }

    @Basic
    @Column(name = "global_score", nullable = true, precision = 0)
    public Double getGlobalScore() {
        return globalScore;
    }

    public void setGlobalScore(Double globalScore) {
        this.globalScore = globalScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestRating that = (TestRating) o;

        if (id != that.id) return false;
        if (polScore != null ? !polScore.equals(that.polScore) : that.polScore != null) return false;
        if (chlScore != null ? !chlScore.equals(that.chlScore) : that.chlScore != null) return false;
        if (globalScore != null ? !globalScore.equals(that.globalScore) : that.globalScore != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (polScore != null ? polScore.hashCode() : 0);
        result = 31 * result + (chlScore != null ? chlScore.hashCode() : 0);
        result = 31 * result + (globalScore != null ? globalScore.hashCode() : 0);
        return result;
    }
}
