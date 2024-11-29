package com.cdac.scanmark.serviceImplementation;

import com.cdac.scanmark.entities.GeoLog;
import com.cdac.scanmark.repository.GeoLogRepository;
import com.cdac.scanmark.service.GeoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeoLogsServiceImpl implements GeoLogService {

    private final GeoLogRepository geoLogRepository;

    @Autowired
    public GeoLogsServiceImpl(GeoLogRepository geoLogRepository) {
        this.geoLogRepository = geoLogRepository;
    }

    @Override
    public List<GeoLog> getAllGeoLogs() {
        return geoLogRepository.findAll();  // Fetch all geo logs
    }

    @Override
    public GeoLog createGeoLogs(GeoLog geoLogs) {
        return geoLogRepository.save(geoLogs);  // Save geo logs
    }

    @Override
    public GeoLog updateGeoLogs(Long id, GeoLog geoLogs) {
        GeoLog existingGeoLog = getGeoLogById(id);  // Fetch existing geo log
        existingGeoLog.setLongitude(geoLogs.getLongitude());  // Update geo log location
        existingGeoLog.setLatitude(geoLogs.getLatitude());
        return geoLogRepository.save(existingGeoLog);  // Save updated geo log
    }

    @Override
    public void deleteGeoLogs(Long id) {
        GeoLog geoLog = getGeoLogById(id);  // Fetch geo log by ID
        geoLogRepository.delete(geoLog);  // Delete geo log
    }

    @Override
    public GeoLog getGeoLogById(Long id) {
        Optional<GeoLog> geoLog = geoLogRepository.findById(id);  // Find geo log by ID
        return geoLog.orElseThrow(() -> new RuntimeException("GeoLog not found with ID: " + id));
    }
}
