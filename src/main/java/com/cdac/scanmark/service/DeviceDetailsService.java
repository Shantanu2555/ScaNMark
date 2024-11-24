package com.cdac.scanmark.service;

import com.cdac.scanmark.entities.DeviceDetails;
import java.util.List;
public interface DeviceDetailsService {
    List<DeviceDetails> getAllDeviceDetails();
    DeviceDetails getDeviceDetailsById(Long id);
    DeviceDetails createDeviceDetails(DeviceDetails deviceDetails);
    DeviceDetails updateDeviceDetails(Long id, DeviceDetails deviceDetails);
    void deleteDeviceDetails(Long id);
}

