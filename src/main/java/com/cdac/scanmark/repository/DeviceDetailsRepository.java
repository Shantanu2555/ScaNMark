package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.DeviceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceDetailsRepository extends JpaRepository<DeviceDetails, Long> {
}
