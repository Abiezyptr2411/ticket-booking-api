package com.busbooking.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busbooking.api.model.Bus;
import com.busbooking.api.repository.BusRepository;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Bus getBusById(Long id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus dengan ID " + id + " tidak ditemukan."));
    }

    public Bus createBus(Bus bus) {
        if (busRepository.existsByName(bus.getName())) {
            throw new RuntimeException("Bus dengan nama '" + bus.getName() + "' sudah ada.");
        }

        return busRepository.save(bus);
    }

    public void deleteBus(Long id) {
        if (!busRepository.existsById(id)) {
            throw new RuntimeException("Bus dengan ID " + id + " tidak ditemukan.");
        }
        busRepository.deleteById(id);
    }
}
