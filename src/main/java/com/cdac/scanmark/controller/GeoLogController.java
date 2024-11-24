package com.cdac.scanmark.controller;

import com.cdac.scanmark.entities.GeoLog;
import com.cdac.scanmark.service.GeoLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/geologs")
public class GeoLogController {

    private final GeoLogService geoLogService;

    public GeoLogController(GeoLogService geoLogService) {
        this.geoLogService = geoLogService;
    }

    @GetMapping
    public ResponseEntity<List<GeoLog>> getAllGeoLogs() {
        return ResponseEntity.ok(geoLogService.getAllGeoLogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeoLog> getGeoLogById(@PathVariable Long id) {
        return ResponseEntity.ok(geoLogService.getGeoLogById(id));
    }

    @PostMapping
    public ResponseEntity<GeoLog> createGeoLog(@RequestBody GeoLog geoLog) {
        return ResponseEntity.ok(geoLogService.createGeoLogs(geoLog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGeoLog(@PathVariable Long id) {
        geoLogService.deleteGeoLogs(id);
        return ResponseEntity.noContent().build();
    }
}
