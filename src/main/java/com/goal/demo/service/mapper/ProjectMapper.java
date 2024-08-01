package com.goal.demo.service.mapper;

import com.goal.demo.domain.Contact;
import com.goal.demo.domain.Project;
import com.goal.demo.service.dto.ContactDTO;
import com.goal.demo.service.dto.ProjectDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Project} and its DTO {@link ProjectDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProjectMapper extends EntityMapper<ProjectDTO, Project> {
    @Mapping(target = "customer", source = "customer")
    ProjectDTO toDto(Project s);

    @Named("contactId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContactDTO toDtoContactId(Contact contact);
}
