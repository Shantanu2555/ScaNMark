package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.GeoLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeoLogRepository extends JpaRepository<GeoLog, Long> {
    @NonNull
    Optional<GeoLog> findById(@NonNull Long id) ;
}
