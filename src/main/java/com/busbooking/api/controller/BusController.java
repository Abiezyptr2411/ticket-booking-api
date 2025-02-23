package com.busbooking.api.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busbooking.api.model.Bus;
import com.busbooking.api.service.BusService;

@RestController
@RequestMapping("/api/buses")
public class BusController {
    @Autowired
    private BusService busService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();
        
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("responseCode", "00");
        response.put("data", buses);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getBusById(@PathVariable Long id) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Bus bus = busService.getBusById(id);
            response.put("status", "success");
            response.put("responseCode", "00");
            response.put("data", bus);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("status", "failed");
            response.put("responseCode", "01");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createBus(@RequestBody Bus bus) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Bus newBus = busService.createBus(bus);
            response.put("status", "success");
            response.put("responseCode", "00");
            response.put("data", newBus);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("status", "failed");
            response.put("responseCode", "01");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteBus(@PathVariable Long id) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            busService.deleteBus(id);
            response.put("status", "success");
            response.put("responseCode", "00");
            response.put("message", "Bus dengan ID " + id + " berhasil dihapus.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("status", "failed");
            response.put("responseCode", "01");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}

