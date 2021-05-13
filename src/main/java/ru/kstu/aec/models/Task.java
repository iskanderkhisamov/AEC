package ru.kstu.aec.models;

import javax.persistence.*;

@Entity
@Table(name = "task", schema = "public", catalog = "omqkpgej")
public class Task {
    private long id;
    private String name;
    private String description;
    private Long paragraphId;
    private String docsFilenames;
    private Double maxScore;

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
    @Column(name = "docs_filenames", nullable = true, length = -1)
    public String getDocsFilenames() {
        return docsFilenames;
    }

    public void setDocsFilenames(String docsFilenames) {
        this.docsFilenames = docsFilenames;
    }

    @Basic
    @Column(name = "max_score", nullable = true, precision = 0)
    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task that = (Task) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (paragraphId != null ? !paragraphId.equals(that.paragraphId) : that.paragraphId != null) return false;
        if (docsFilenames != null ? !docsFilenames.equals(that.docsFilenames) : that.docsFilenames != null)
            return false;
        if (maxScore != null ? !maxScore.equals(that.maxScore) : that.maxScore != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (paragraphId != null ? paragraphId.hashCode() : 0);
        result = 31 * result + (docsFilenames != null ? docsFilenames.hashCode() : 0);
        result = 31 * result + (maxScore != null ? maxScore.hashCode() : 0);
        return result;
    }
}
