package com.goal.demo.web.rest;

import com.goal.demo.service.ProgressService;
import com.goal.demo.service.dto.ProgressDTO;
import com.goal.demo.service.impl.exception.BadRequestException;
import com.goal.demo.web.rest.utils.HeaderUtil;
import com.goal.demo.web.rest.utils.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link com.goal.demo.domain.Progress}.
 */
@RestController
@RequestMapping("/api/progresses")
public class ProgressResource {

    private final Logger log = LoggerFactory.getLogger(ProgressResource.class);

    private static final String ENTITY_NAME = "progress";

    @Value("${spring.application.name}")
    private String applicationName;

    private final ProgressService progressService;

    public ProgressResource(ProgressService progressService) {
        this.progressService = progressService;
    }

    /**
     * {@code POST  /progresses} : Create a new progress.
     *
     * @param progressDTO the progressDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new progressDTO, or with status {@code 400 (Bad Request)} if the progress has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ProgressDTO> createProgress(@RequestBody ProgressDTO progressDTO) throws URISyntaxException {
        log.debug("REST request to save Progress : {}", progressDTO);
        ProgressDTO result = progressService.save(progressDTO);
        return ResponseEntity
                .created(new URI("/api/progresses/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /progresses/:id} : Updates an existing progress.
     *
     * @param id the id of the progressDTO to save.
     * @param progressDTO the progressDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated progressDTO,
     * or with status {@code 400 (Bad Request)} if the progressDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the progressDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProgressDTO> updateProgress(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProgressDTO progressDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Progress : {}, {}", id, progressDTO);
        if (!Objects.equals(id, progressDTO.getId())) {
            throw new BadRequestException("Invalid ID");
        }
        ProgressDTO result = progressService.update(progressDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, progressDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /progresses} : get all the progresses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of progresses in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ProgressDTO>> getAllProgresses(Pageable pageable,
        @RequestParam Long projectId
    ) {
        log.debug("REST request to get a page of Progresses");
        Page<ProgressDTO> page = progressService.findAll(pageable, projectId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /progresses/:id} : get the "id" progress.
     *
     * @param id the id of the progressDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the progressDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProgressDTO> getProgress(@PathVariable Long id) {
        log.debug("REST request to get Progress : {}", id);
        Optional<ProgressDTO> progressDTO = progressService.findOne(id);
        return progressDTO.map(dto -> ResponseEntity.ok().body(dto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * {@code DELETE  /progresses/:id} : delete the "id" progress.
     *
     * @param id the id of the progressDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgress(@PathVariable Long id) {
        log.debug("REST request to delete Progress : {}", id);
        progressService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, ENTITY_NAME, id.toString()))
                .build();
    }
}
