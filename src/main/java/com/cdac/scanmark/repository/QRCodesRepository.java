package com.cdac.scanmark.repository;

import com.cdac.scanmark.entities.QRCodes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QRCodesRepository extends JpaRepository<QRCodes, Long> {
}
