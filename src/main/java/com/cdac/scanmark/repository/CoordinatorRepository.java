package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {

    @NonNull
    // Find a coordinator by their unique coordinator ID
    Optional<Coordinator> findById(@NonNull Long id);

    Optional<Coordinator> findByEmail(@NonNull String email) ;

    boolean existsByEmail(String email) ;


}
