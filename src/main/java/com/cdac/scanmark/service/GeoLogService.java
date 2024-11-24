package com.cdac.scanmark.service;

import com.cdac.scanmark.entities.GeoLog;
import java.util.List;
public interface GeoLogService {
    List<GeoLog> getAllGeoLogs();
    GeoLog createGeoLogs(GeoLog geoLogs);
    GeoLog updateGeoLogs(Long id, GeoLog geoLogs);
    void deleteGeoLogs(Long id);

    GeoLog getGeoLogById(Long id);
}

