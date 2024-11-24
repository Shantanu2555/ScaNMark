package com.cdac.scanmark.controller;

import com.cdac.scanmark.entities.DeviceDetails;
import com.cdac.scanmark.service.DeviceDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devicedetails")
public class DeviceDetailsController {

    private final DeviceDetailsService deviceDetailService;

    public DeviceDetailsController(DeviceDetailsService deviceDetailService) {
        this.deviceDetailService = deviceDetailService;
    }

    @GetMapping
    public ResponseEntity<List<DeviceDetails>> getAllDeviceDetails() {
        return ResponseEntity.ok(deviceDetailService.getAllDeviceDetails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDetails> getDeviceDetailById(@PathVariable Long id) {
        return ResponseEntity.ok(deviceDetailService.getDeviceDetailsById(id));
    }

    @PostMapping
    public ResponseEntity<DeviceDetails> createDeviceDetail(@RequestBody DeviceDetails deviceDetail) {
        return ResponseEntity.ok(deviceDetailService.createDeviceDetails(deviceDetail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeviceDetail(@PathVariable Long id) {
        deviceDetailService.deleteDeviceDetails(id);
        return ResponseEntity.noContent().build();
    }
}
