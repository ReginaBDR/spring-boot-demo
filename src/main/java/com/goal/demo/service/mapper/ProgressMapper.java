package com.goal.demo.service.mapper;

import com.goal.demo.domain.Progress;
import com.goal.demo.service.dto.ProgressDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Progress} and its DTO {@link ProgressDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProgressMapper extends EntityMapper<ProgressDTO, Progress> {}
