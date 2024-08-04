package com.goal.demo.service.impl;

import com.goal.demo.domain.Contact;
import com.goal.demo.repository.ContactRepository;
import com.goal.demo.service.ContactService;
import com.goal.demo.service.dto.ContactDTO;
import com.goal.demo.service.impl.exception.BadRequestException;
import com.goal.demo.service.impl.exception.NotFoundException;
import com.goal.demo.service.mapper.ContactMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        if (contactDTO.getId() != null) {
            throw new BadRequestException("A new contact cannot already have an ID");
        }

        Contact contact = contactMapper.toEntity(contactDTO);
        contact = contactRepository.save(contact);
        return contactMapper.toDto(contact);
    }

    @Override
    public ContactDTO update(ContactDTO contactDTO) {
        log.debug("Request to update Contact : {}", contactDTO);
        if (contactDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        if (!contactRepository.existsById(contactDTO.getId())) {
            throw new NotFoundException("Entity not found");
        }

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
