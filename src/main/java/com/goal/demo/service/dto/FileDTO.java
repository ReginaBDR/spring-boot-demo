package com.goal.demo.service.dto;

import jakarta.persistence.Lob;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * A DTO for the {@link com.goal.demo.domain.File} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FileDTO implements Serializable {

    private Long id;

    private String name;

    @Lob
    private byte[] file;

    private String fileContentType;

    private String description;

    private Long projectId;

    public FileDTO(String s, String baseName) {
    }

    public FileDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProjectId() {return projectId;}

    public void setProjectId(Long projectId) {this.projectId = projectId;}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileDTO)) {
            return false;
        }

        FileDTO fileDTO = (FileDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, fileDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "FileDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", file=" + Arrays.toString(file) +
                ", fileContentType='" + fileContentType + '\'' +
                ", description='" + description + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
