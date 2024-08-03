package com.goal.demo.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Arrays;

/**
 * A File.
 */
@Entity
@Table(name = "file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "file")
    private byte[] file;

    @Column(name = "file_content_type")
    private String fileContentType;

    @Column(name = "description")
    private String description;

    @Column(name = "project_id")
    private Long projectId;

    public Long getId() {
        return this.id;
    }

    public File id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public File name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return this.file;
    }

    public File file(byte[] file) {
        this.setFile(file);
        return this;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return this.fileContentType;
    }

    public File fileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
        return this;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getDescription() {
        return this.description;
    }

    public File description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public File projectId(Long projectId) {
        this.projectId = projectId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof File)) {
            return false;
        }
        return getId() != null && getId().equals(((File) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", file=" + Arrays.toString(file) +
                ", fileContentType='" + fileContentType + '\'' +
                ", description='" + description + '\'' +
                ", projectId=" + projectId +
                '}';
    }
}
