package com.cdac.scanmark.serviceImplementation;

import com.cdac.scanmark.entities.DeviceDetails;
import com.cdac.scanmark.repository.DeviceDetailsRepository;
import com.cdac.scanmark.service.DeviceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceDetailsServiceImpl implements DeviceDetailsService {

    private final DeviceDetailsRepository deviceDetailsRepository;

    @Autowired
    public DeviceDetailsServiceImpl(DeviceDetailsRepository deviceDetailsRepository) {
        this.deviceDetailsRepository = deviceDetailsRepository;
    }

    @Override
    public List<DeviceDetails> getAllDeviceDetails() {
        return deviceDetailsRepository.findAll();  // Fetch all device details
    }

    @Override
    public DeviceDetails getDeviceDetailsById(Long id) {
        Optional<DeviceDetails> deviceDetails = deviceDetailsRepository.findById(id);  // Find device details by ID
        return deviceDetails.orElseThrow(() -> new RuntimeException("Device not found with ID: " + id));
    }

    @Override
    public DeviceDetails createDeviceDetails(DeviceDetails deviceDetails) {
        return deviceDetailsRepository.save(deviceDetails);  // Save the device details
    }

    @Override
    public DeviceDetails updateDeviceDetails(Long id, DeviceDetails deviceDetails) {
        DeviceDetails existingDeviceDetails = getDeviceDetailsById(id);  // Fetch existing device details
        existingDeviceDetails.setDeviceName(deviceDetails.getDeviceName());  // Update device details
        return deviceDetailsRepository.save(existingDeviceDetails);  // Save updated device details
    }

    @Override
    public void deleteDeviceDetails(Long id) {
        DeviceDetails deviceDetails = getDeviceDetailsById(id);  // Fetch device details by ID
        deviceDetailsRepository.delete(deviceDetails);  // Delete device details
    }
}
