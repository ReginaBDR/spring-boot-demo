package com.goal.demo.repository;

import com.goal.demo.domain.Progress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the Progress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    @Query(value = "SELECT * FROM progress p WHERE p.id = :progressId", nativeQuery = true)
    Optional<Progress> findProgressByIdWithAudit(@Param("progressId") Long progressId);

    @Query(value = "SELECT * FROM progress p WHERE p.project_id = :projectId", nativeQuery = true)
    Page<Progress> findAllProgressesByProjectIdWithAudit(Pageable pageable, @Param("projectId") Long projectId);
}
