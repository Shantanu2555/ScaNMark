package com.cdac.scanmark.serviceImplementation;

import com.cdac.scanmark.entities.QRCodes;
import com.cdac.scanmark.service.QRCodesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QRCodesServiceImpl implements QRCodesService {
    @Override
    public List<QRCodes> getAllQRCodes() {
        return null;
    }

    @Override
    public QRCodes getQRCodeById(Long id) {
        return null;
    }

    @Override
    public QRCodes createQRCode(QRCodes qrCode) {
        return null;
    }

    @Override
    public QRCodes updateQRCode(Long id, QRCodes qrCode) {
        return null;
    }

    @Override
    public void deleteQRCode(Long id) {

    }
}
