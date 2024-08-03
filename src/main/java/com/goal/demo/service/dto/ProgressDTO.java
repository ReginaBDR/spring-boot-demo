package com.goal.demo.service.dto;

import com.goal.demo.domain.AbstractAuditingEntity;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.goal.demo.domain.Progress} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProgressDTO extends AbstractAuditingEntity implements Serializable {

    private Long id;

    private String notes;

    private String link;

    private Long projectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getProjectId() {return projectId;}

    public void setProjectId(Long projectId) {this.projectId = projectId;}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProgressDTO)) {
            return false;
        }

        ProgressDTO progressDTO = (ProgressDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, progressDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "ProgressDTO{" +
                "id=" + id +
                ", notes='" + notes + '\'' +
                ", link='" + link + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
