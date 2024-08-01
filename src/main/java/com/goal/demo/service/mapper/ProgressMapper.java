package com.goal.demo.service.mapper;

import com.goal.demo.domain.Contact;
import com.goal.demo.domain.Progress;
import com.goal.demo.domain.Project;
import com.goal.demo.service.dto.ContactDTO;
import com.goal.demo.service.dto.ProgressDTO;
import com.goal.demo.service.dto.ProjectDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Progress} and its DTO {@link ProgressDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProgressMapper extends EntityMapper<ProgressDTO, Progress> {
    @Mapping(target = "contact", source = "contact")
    @Mapping(target = "project", source = "project", qualifiedByName = "projectId")
    ProgressDTO toDto(Progress s);

    @Named("contactId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContactDTO toDtoContactId(Contact contact);

    @Named("projectId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProjectDTO toDtoProjectId(Project project);
}
