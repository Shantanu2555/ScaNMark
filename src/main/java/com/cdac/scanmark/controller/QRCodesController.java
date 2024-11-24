package com.cdac.scanmark.controller;

import com.cdac.scanmark.entities.QRCodes;
import com.cdac.scanmark.service.QRCodesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qrcodes")
public class QRCodesController {

    private final QRCodesService qrCodesService;

    public QRCodesController(QRCodesService qrCodeService) {
        this.qrCodesService = qrCodeService;
    }

    @GetMapping
    public ResponseEntity<List<QRCodes>> getAllQRCodes() {
        return ResponseEntity.ok(qrCodesService.getAllQRCodes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QRCodes> getQRCodeById(@PathVariable Long id) {
        return ResponseEntity.ok(qrCodesService.getQRCodeById(id));
    }

    @PostMapping
    public ResponseEntity<QRCodes> createQRCode(@RequestBody QRCodes qrCode) {
        return ResponseEntity.ok(qrCodesService.createQRCode(qrCode));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQRCode(@PathVariable Long id) {
        qrCodesService.deleteQRCode(id);
        return ResponseEntity.noContent().build();
    }
}
