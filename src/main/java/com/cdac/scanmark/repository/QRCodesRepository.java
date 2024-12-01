package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.QRCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QRCodesRepository extends JpaRepository<QRCodes, Long> {

    @NonNull
    Optional<QRCodes> findById(@NonNull Long id) ;
}
