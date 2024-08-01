package com.goal.demo.service.mapper;

import com.goal.demo.domain.File;
import com.goal.demo.domain.Progress;
import com.goal.demo.domain.Project;
import com.goal.demo.service.dto.FileDTO;
import com.goal.demo.service.dto.ProgressDTO;
import com.goal.demo.service.dto.ProjectDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link File} and its DTO {@link FileDTO}.
 */
@Mapper(componentModel = "spring")
public interface FileMapper extends EntityMapper<FileDTO, File> {
    @Mapping(target = "project", source = "project", qualifiedByName = "projectId")
    @Mapping(target = "progress", source = "progress", qualifiedByName = "progressId")
    FileDTO toDto(File s);

    @Named("projectId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProjectDTO toDtoProjectId(Project project);

    @Named("progressId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProgressDTO toDtoProgressId(Progress progress);
}
