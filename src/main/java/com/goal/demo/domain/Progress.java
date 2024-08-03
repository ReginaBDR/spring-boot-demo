package com.goal.demo.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

/**
 * A Progress.
 */
@Entity
@Table(name = "progress")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EntityListeners(AuditingEntityListener.class)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Progress extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "notes")
    private String notes;

    @Column(name = "link")
    private String link;

    @Column(name = "project_id")
    private Long projectId;

    public Long getId() {
        return this.id;
    }

    public Progress id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotes() {
        return this.notes;
    }

    public Progress notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLink() {
        return this.link;
    }

    public Progress link(String link) {
        this.setLink(link);
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Progress projectId(Long projectId) {
        this.projectId = projectId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Progress)) {
            return false;
        }
        return getId() != null && getId().equals(((Progress) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Progress{" +
                "id=" + id +
                ", notes='" + notes + '\'' +
                ", link='" + link + '\'' +
                ", projectId=" + projectId +
                '}';
    }
}
