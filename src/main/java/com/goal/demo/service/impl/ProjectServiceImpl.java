package com.goal.demo.service.impl;

import com.goal.demo.domain.Project;
import com.goal.demo.repository.ProjectRepository;
import com.goal.demo.service.ProjectService;
import com.goal.demo.service.dto.ProjectDTO;
import com.goal.demo.service.impl.exception.BadRequestException;
import com.goal.demo.service.impl.exception.NotFoundException;
import com.goal.demo.service.mapper.ProjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link com.goal.demo.domain.Project}.
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public ProjectDTO save(ProjectDTO projectDTO) {
        log.debug("Request to save Project : {}", projectDTO);
        if (projectDTO.getId() != null) {
            throw new BadRequestException("A new project cannot already have an ID");
        }

        Project project = projectMapper.toEntity(projectDTO);
        project = projectRepository.save(project);
        return projectMapper.toDto(project);
    }

    @Override
    public ProjectDTO update(ProjectDTO projectDTO) {
        log.debug("Request to update Project : {}", projectDTO);
        if (projectDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        if (!projectRepository.existsById(projectDTO.getId())) {
            throw new NotFoundException("Entity not found");
        }

        Project project = projectMapper.toEntity(projectDTO);
        project = projectRepository.save(project);
        return projectMapper.toDto(project);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProjectDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Projects");
        return projectRepository.findAll(pageable).map(projectMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectDTO> findOne(Long id) {
        log.debug("Request to get Project : {}", id);
        return projectRepository.findById(id).map(projectMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Project : {}", id);
        projectRepository.deleteById(id);
    }
}
