package com.cdac.scanmark.service;

import com.cdac.scanmark.entities.QRCodes;
import java.util.List;
public interface QRCodesService {
    List<QRCodes> getAllQRCodes();
    QRCodes getQRCodeById(Long id);
    QRCodes createQRCode(QRCodes qrCode);
    QRCodes updateQRCode(Long id, QRCodes qrCode);
    void deleteQRCode(Long id);
}

