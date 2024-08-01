package com.goal.demo.service.impl;

import com.goal.demo.domain.Contact;
import com.goal.demo.repository.ContactRepository;
import com.goal.demo.service.ContactService;
import com.goal.demo.service.dto.ContactDTO;
import com.goal.demo.service.mapper.ContactMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link com.goal.demo.domain.Contact}.
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    private final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);

    private final ContactRepository contactRepository;

    private final ContactMapper contactMapper;

    public ContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public ContactDTO save(ContactDTO contactDTO) {
        log.debug("Request to save Contact : {}", contactDTO);
        Contact contact = contactMapper.toEntity(contactDTO);
        contact = contactRepository.save(contact);
        return contactMapper.toDto(contact);
    }

    @Override
    public ContactDTO update(ContactDTO contactDTO) {
        log.debug("Request to update Contact : {}", contactDTO);
        Contact contact = contactMapper.toEntity(contactDTO);
        contact = contactRepository.save(contact);
        return contactMapper.toDto(contact);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ContactDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Contacts");
        return contactRepository.findAll(pageable).map(contactMapper::toDto);
    }

    /**
     *  Get all the contacts where Project is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ContactDTO> findAllWhereProjectIsNull() {
        log.debug("Request to get all contacts where Project is null");
        return StreamSupport
            .stream(contactRepository.findAll().spliterator(), false)
            .filter(contact -> contact.getProject() == null)
            .map(contactMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the contacts where Progress is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ContactDTO> findAllWhereProgressIsNull() {
        log.debug("Request to get all contacts where Progress is null");
        return StreamSupport
            .stream(contactRepository.findAll().spliterator(), false)
            .filter(contact -> contact.getProgress() == null)
            .map(contactMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ContactDTO> findOne(Long id) {
        log.debug("Request to get Contact : {}", id);
        return contactRepository.findById(id).map(contactMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Contact : {}", id);
        contactRepository.deleteById(id);
    }
}
