package com.cdac.scanmark.serviceImplementation;

import com.cdac.scanmark.entities.QRCodes;
import com.cdac.scanmark.repository.QRCodesRepository;
import com.cdac.scanmark.service.QRCodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QRCodesServiceImpl implements QRCodesService {

    private final QRCodesRepository qrCodesRepository;

    @Autowired
    public QRCodesServiceImpl(QRCodesRepository qrCodesRepository) {
        this.qrCodesRepository = qrCodesRepository;
    }

    @Override
    public List<QRCodes> getAllQRCodes() {
        return qrCodesRepository.findAll();  // Fetch all QR codes
    }

    @Override
    public QRCodes getQRCodeById(Long id) {
        Optional<QRCodes> qrCode = qrCodesRepository.findById(id);  // Find QR code by ID
        return qrCode.orElseThrow(() -> new RuntimeException("QR Code not found with ID: " + id));
    }

    @Override
    public QRCodes createQRCode(QRCodes qrCode) {
        return qrCodesRepository.save(qrCode);  // Save new QR code
    }

    @Override
    public QRCodes updateQRCode(Long id, QRCodes qrCode) {
        QRCodes existingQRCode = getQRCodeById(id);  // Fetch existing QR code
        existingQRCode.setQrData(qrCode.getQrData());  // Update QR code data
        return qrCodesRepository.save(existingQRCode);  // Save updated QR code
    }

    @Override
    public void deleteQRCode(Long id) {
        QRCodes qrCode = getQRCodeById(id);  // Fetch QR code by ID
        qrCodesRepository.delete(qrCode);  // Delete QR code
    }
}
