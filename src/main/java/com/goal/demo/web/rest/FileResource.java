package com.goal.demo.web.rest;

import com.goal.demo.service.FileService;
import com.goal.demo.service.dto.FileDTO;
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
 * REST controller for managing {@link com.goal.demo.domain.File}.
 */
@RestController
@RequestMapping("/api/files")
public class FileResource {

    private final Logger log = LoggerFactory.getLogger(FileResource.class);

    private static final String ENTITY_NAME = "file";

    @Value("${spring.application.name}")
    private String applicationName;

    private final FileService fileService;


    public FileResource(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * {@code POST  /files} : Create a new file.
     *
     * @param fileDTO the fileDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileDTO, or with status {@code 400 (Bad Request)} if the file has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FileDTO> createFile(@RequestBody FileDTO fileDTO) throws URISyntaxException {
        log.debug("REST request to save File : {}", fileDTO);
        FileDTO result = fileService.save(fileDTO);
        return ResponseEntity
                .created(new URI("/api/files/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /files/:id} : Updates an existing file.
     *
     * @param id the id of the fileDTO to save.
     * @param fileDTO the fileDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileDTO,
     * or with status {@code 400 (Bad Request)} if the fileDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fileDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FileDTO> updateFile(@PathVariable(value = "id", required = false) final Long id, @RequestBody FileDTO fileDTO)
        throws URISyntaxException {
        log.debug("REST request to update File : {}, {}", id, fileDTO);
        if (!Objects.equals(id, fileDTO.getId())) {
            throw new BadRequestException("Invalid ID");
        }
        FileDTO result = fileService.update(fileDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, fileDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /files} : get all the files.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of files in body.
     */
    @GetMapping("")
    public ResponseEntity<List<FileDTO>> getAllFiles(Pageable pageable, @RequestParam Long projectId) {
        log.debug("REST request to get a page of Files");
        Page<FileDTO> page = fileService.findAll(pageable, projectId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /files/:id} : get the "id" file.
     *
     * @param id the id of the fileDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FileDTO> getFile(@PathVariable Long id) {
        log.debug("REST request to get File : {}", id);
        Optional<FileDTO> fileDTO = fileService.findOne(id);
        return fileDTO.map(dto -> ResponseEntity.ok().body(dto)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * {@code DELETE  /files/:id} : delete the "id" file.
     *
     * @param id the id of the fileDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
        log.debug("REST request to delete File : {}", id);
        fileService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, ENTITY_NAME, id.toString()))
                .build();
    }
}
