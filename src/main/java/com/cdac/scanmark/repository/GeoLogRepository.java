package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.GeoLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoLogRepository extends JpaRepository<GeoLog, Long> {
}
