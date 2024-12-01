package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.DeviceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceDetailsRepository extends JpaRepository<DeviceDetails, Long> {

    @NonNull
    Optional<DeviceDetails> findById(@NonNull Long id) ;
}
