package com.goal.demo.repository;

import com.goal.demo.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Contact entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {}
