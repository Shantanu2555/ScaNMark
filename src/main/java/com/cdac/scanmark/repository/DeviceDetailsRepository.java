package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.DeviceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface DeviceDetailsRepository extends JpaRepository<DeviceDetails, Long> {

    @NonNull
    Optional<DeviceDetails> findById(@NonNull Long id) ;
}
