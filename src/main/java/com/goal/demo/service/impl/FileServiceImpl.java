package com.goal.demo.service.impl;

import com.goal.demo.domain.File;
import com.goal.demo.repository.FileRepository;
import com.goal.demo.service.FileService;
import com.goal.demo.service.dto.FileDTO;
import com.goal.demo.service.impl.exception.BadRequestException;
import com.goal.demo.service.impl.exception.NotFoundException;
import com.goal.demo.service.mapper.FileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * Service Implementation for managing {@link com.goal.demo.domain.File}.
 */
@Service
@Transactional
public class FileServiceImpl implements FileService {
    private final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    public FileServiceImpl(FileRepository fileRepository, FileMapper fileMapper) {
        this.fileRepository = fileRepository;
        this.fileMapper = fileMapper;
    }

    @Override
    public FileDTO save(FileDTO fileDTO) {
        log.debug("Request to save File : {}", fileDTO);
        if (fileDTO.getId() != null) {
            throw new BadRequestException("A new file cannot already have an ID");
        }

        File file = fileMapper.toEntity(fileDTO);
        file = fileRepository.save(file);
        return fileMapper.toDto(file);
    }

    @Override
    public FileDTO update(FileDTO fileDTO) {
        log.debug("Request to update File : {}", fileDTO);
        if (fileDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        if (!fileRepository.existsById(fileDTO.getId())) {
            throw new NotFoundException("Entity not found");
        }

        File file = fileMapper.toEntity(fileDTO);
        file = fileRepository.save(file);
        return fileMapper.toDto(file);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FileDTO> findAll(Pageable pageable, Long projectId) {
        log.debug("Request to get all Files");
        return fileRepository.findAllFilesByProjectId(pageable, projectId).map(fileMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FileDTO> findOne(Long id) {
        log.debug("Request to get File : {}", id);
        return fileRepository.findById(id).map(fileMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete File : {}", id);
        fileRepository.deleteById(id);
    }

}
