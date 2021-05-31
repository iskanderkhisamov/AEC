package ru.kstu.aec.models;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "theme", schema = "public", catalog = "omqkpgej")
public class Theme {

    private long id;
    private String name;
    private String description;
    private String docsFilenames;
    private Double maxPolScore;
    private Double maxChlScore;
    private Double maxUprScore;
    private Double maxGlobalScore;
    private Set<Paragraph> paragraphs;
    @ManyToOne
    @JoinColumn(name="course_id", nullable = false)
    private Course course;

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
    @Column(name = "docs_filenames", nullable = true, length = -1)
    public String getDocsFilenames() {
        return docsFilenames;
    }

    public void setDocsFilenames(String docsFilenames) {
        this.docsFilenames = docsFilenames;
    }

    @Basic
    @Column(name = "max_pol_score", nullable = true, precision = 0)
    public Double getMaxPolScore() {
        return maxPolScore;
    }

    public void setMaxPolScore(Double maxPolScore) {
        this.maxPolScore = maxPolScore;
    }

    @Basic
    @Column(name = "max_chl_score", nullable = true, precision = 0)
    public Double getMaxChlScore() {
        return maxChlScore;
    }

    public void setMaxChlScore(Double maxChlScore) {
        this.maxChlScore = maxChlScore;
    }

    @Basic
    @Column(name = "max_upr_score", nullable = true, precision = 0)
    public Double getMaxUprScore() {
        return maxUprScore;
    }

    public void setMaxUprScore(Double maxUprScore) {
        this.maxUprScore = maxUprScore;
    }

    @Basic
    @Column(name = "max_global_score", nullable = true, precision = 0)
    public Double getMaxGlobalScore() {
        return maxGlobalScore;
    }

    public void setMaxGlobalScore(Double maxGlobalScore) {
        this.maxGlobalScore = maxGlobalScore;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @OneToMany(mappedBy = "theme")
    public Set<Paragraph> getParagraphs() {
        return paragraphs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Theme that = (Theme) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (docsFilenames != null ? !docsFilenames.equals(that.docsFilenames) : that.docsFilenames != null)
            return false;
        if (maxPolScore != null ? !maxPolScore.equals(that.maxPolScore) : that.maxPolScore != null) return false;
        if (maxChlScore != null ? !maxChlScore.equals(that.maxChlScore) : that.maxChlScore != null) return false;
        if (maxUprScore != null ? !maxUprScore.equals(that.maxUprScore) : that.maxUprScore != null) return false;
        if (maxGlobalScore != null ? !maxGlobalScore.equals(that.maxGlobalScore) : that.maxGlobalScore != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (docsFilenames != null ? docsFilenames.hashCode() : 0);
        result = 31 * result + (maxPolScore != null ? maxPolScore.hashCode() : 0);
        result = 31 * result + (maxChlScore != null ? maxChlScore.hashCode() : 0);
        result = 31 * result + (maxUprScore != null ? maxUprScore.hashCode() : 0);
        result = 31 * result + (maxGlobalScore != null ? maxGlobalScore.hashCode() : 0);
        return result;
    }
}
