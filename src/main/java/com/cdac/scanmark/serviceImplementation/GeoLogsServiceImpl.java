package com.cdac.scanmark.serviceImplementation;

import com.cdac.scanmark.entities.GeoLog;
import com.cdac.scanmark.service.GeoLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeoLogsServiceImpl implements GeoLogService {
    @Override
    public List<GeoLog> getAllGeoLogs() {
        return null;
    }

    @Override
    public GeoLog createGeoLogs(GeoLog geoLogs) {
        return null;
    }

    @Override
    public GeoLog updateGeoLogs(Long id, GeoLog geoLogs) {
        return null;
    }

    @Override
    public void deleteGeoLogs(Long id) {

    }

    @Override
    public GeoLog getGeoLogById(Long id) {
        return null;
    }
}
