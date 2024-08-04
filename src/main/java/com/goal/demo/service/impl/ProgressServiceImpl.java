package com.goal.demo.service.impl;

import com.goal.demo.domain.Progress;
import com.goal.demo.repository.ProgressRepository;
import com.goal.demo.service.ProgressService;
import com.goal.demo.service.dto.ProgressDTO;
import com.goal.demo.service.impl.exception.BadRequestException;
import com.goal.demo.service.impl.exception.NotFoundException;
import com.goal.demo.service.mapper.ProgressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link com.goal.demo.domain.Progress}.
 */
@Service
@Transactional
public class ProgressServiceImpl implements ProgressService {

    private final Logger log = LoggerFactory.getLogger(ProgressServiceImpl.class);
    private final ProgressRepository progressRepository;
    private final ProgressMapper progressMapper;

    public ProgressServiceImpl(
        ProgressRepository progressRepository,
        ProgressMapper progressMapper
    ) {
        this.progressRepository = progressRepository;
        this.progressMapper = progressMapper;
    }

    @Override
    public ProgressDTO save(ProgressDTO progressDTO) {
        log.debug("Request to save Progress : {}", progressDTO);
        if (progressDTO.getId() != null) {
            throw new BadRequestException("A new progress cannot already have an ID");
        }

        Progress progress = progressMapper.toEntity(progressDTO);
        progress = progressRepository.save(progress);
        return progressMapper.toDto(progress);
    }

    @Override
    public ProgressDTO update(ProgressDTO progressDTO) {
        log.debug("Request to update Progress : {}", progressDTO);
        if (progressDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        if (!progressRepository.existsById(progressDTO.getId())) {
            throw new NotFoundException("Entity not found");
        }

        Progress progress = progressMapper.toEntity(progressDTO);
        progress = progressRepository.save(progress);
        return progressMapper.toDto(progress);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProgressDTO> findAll(Pageable pageable, Long projectId) {
        log.debug("Request to get all Progresses by project:", projectId);
        return progressRepository.findAllByProjectId(pageable, projectId).map(progressMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProgressDTO> findOne(Long id) {
        log.debug("Request to get Progress : {}", id);
        return progressRepository.findById(id).map(progressMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Progress : {}", id);
        progressRepository.deleteById(id);
    }
}
