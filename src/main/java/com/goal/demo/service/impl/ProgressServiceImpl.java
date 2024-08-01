package com.goal.demo.service.impl;

import com.goal.demo.domain.Progress;
import com.goal.demo.repository.ProgressRepository;
import com.goal.demo.service.ProgressService;
import com.goal.demo.service.dto.ProgressAuditedDTO;
import com.goal.demo.service.dto.ProgressDTO;
import com.goal.demo.service.mapper.ContactMapper;
import com.goal.demo.service.mapper.ProgressMapper;
import com.goal.demo.service.mapper.ProjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link com.goal.demo.domain.Progress}.
 */
@Service
@Transactional
public class ProgressServiceImpl implements ProgressService {

    private final Logger log = LoggerFactory.getLogger(ProgressServiceImpl.class);
    private final ProgressRepository progressRepository;
    private final ProgressMapper progressMapper;
    private final ContactMapper contactMapper;
    private final ProjectMapper projectMapper;

    public ProgressServiceImpl(
        ProgressRepository progressRepository,
        ProgressMapper progressMapper,
        ContactMapper contactMapper,
        ProjectMapper projectMapper
    ) {
        this.progressRepository = progressRepository;
        this.progressMapper = progressMapper;
        this.contactMapper = contactMapper;
        this.projectMapper = projectMapper;
    }

    @Override
    public ProgressDTO save(ProgressDTO progressDTO) {
        log.debug("Request to save Progress : {}", progressDTO);
        Progress progress = progressMapper.toEntity(progressDTO);
        progress = progressRepository.save(progress);
        return progressMapper.toDto(progress);
    }

    @Override
    public ProgressDTO update(ProgressDTO progressDTO) {
        log.debug("Request to update Progress : {}", progressDTO);
        Progress progress = progressMapper.toEntity(progressDTO);
        progress = progressRepository.save(progress);
        return progressMapper.toDto(progress);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProgressAuditedDTO> findAll(Pageable pageable, Long projectId) {
        log.debug("Request to get all Progresses by project:", projectId);
        Page<Progress> resultPage = progressRepository.findAllProgressesByProjectIdWithAudit(pageable, projectId);

        List<ProgressAuditedDTO> progressAuditedDTOList = resultPage
            .getContent()
            .stream()
            .map(row ->
                new ProgressAuditedDTO(
                    row.getId(),
                    row.getNotes(),
                    row.getLink(),
                    contactMapper.toDto(row.getContact()),
                    projectMapper.toDto(row.getProject()),
                    row.getCreatedBy(),
                    row.getCreatedDate(),
                    row.getLastModifiedBy(),
                    row.getLastModifiedDate()
                )
            )
            .collect(Collectors.toList());

        return new PageImpl<>(progressAuditedDTOList, pageable, resultPage.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProgressDTO> findOne(Long id) {
        log.debug("Request to get Progress : {}", id);
        return progressRepository.findById(id).map(progressMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProgressAuditedDTO> findOneWithAudit(Long id) {
        log.debug("Request to get Progress : {}", id);
        Optional<Progress> progressOptional = progressRepository.findProgressByIdWithAudit(id);
        if (progressOptional.isPresent()) {
            return progressOptional.map(row ->
                new ProgressAuditedDTO(
                    row.getId(),
                    row.getNotes(),
                    row.getLink(),
                    contactMapper.toDto(row.getContact()),
                    projectMapper.toDto(row.getProject()),
                    row.getCreatedBy(),
                    row.getCreatedDate(),
                    row.getLastModifiedBy(),
                    row.getLastModifiedDate()
                )
            );
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Progress : {}", id);
        progressRepository.deleteById(id);
    }
}
