package com.goal.demo.service;

import com.goal.demo.service.dto.ProgressAuditedDTO;
import com.goal.demo.service.dto.ProgressDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.goal.demo.domain.Progress}.
 */
public interface ProgressService {
    /**
     * Save a progress.
     *
     * @param progressDTO the entity to save.
     * @return the persisted entity.
     */
    ProgressDTO save(ProgressDTO progressDTO);

    /**
     * Updates a progress.
     *
     * @param progressDTO the entity to update.
     * @return the persisted entity.
     */
    ProgressDTO update(ProgressDTO progressDTO);

    /**
     * Get all the progresses by project.
     *
     * @param pageable  the pagination information.
     * @param projectId the project of the entity.
     * @return the list of entities.
     */
    Page<ProgressAuditedDTO> findAll(Pageable pageable, Long projectId);

    /**
     * Get the "id" progress.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProgressDTO> findOne(Long id);

    /**
     * Get the "id" progress.
     *
     * @param id the id of the entity.
     * @return the entity with Audited fields.
     */
    Optional<ProgressAuditedDTO> findOneWithAudit(Long id);

    /**
     * Delete the "id" progress.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
