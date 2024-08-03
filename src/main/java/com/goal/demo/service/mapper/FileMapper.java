package com.goal.demo.service.mapper;

import com.goal.demo.domain.File;
import com.goal.demo.service.dto.FileDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link File} and its DTO {@link FileDTO}.
 */
@Mapper(componentModel = "spring")
public interface FileMapper extends EntityMapper<FileDTO, File> {}
