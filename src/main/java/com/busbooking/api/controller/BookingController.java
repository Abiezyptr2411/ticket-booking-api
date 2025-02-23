// package com.busbooking.api.controller;

// import java.util.LinkedHashMap;
// import java.util.List;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.busbooking.api.model.Booking;
// import com.busbooking.api.service.BookingService;

// @RestController
// @RequestMapping("/api/bookings")
// public class BookingController {
//     @Autowired
//     private BookingService bookingService;

//     @GetMapping
//     public ResponseEntity<Map<String, Object>> getAllBookings() {
//         List<Booking> bookings = bookingService.getAllBookings();

//         Map<String, Object> response = new LinkedHashMap<>();
//         response.put("status", "success");
//         response.put("responseCode", "00");
//         response.put("data", bookings);

//         return ResponseEntity.ok(response);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Map<String, Object>> getBookingById(@PathVariable Long id) {
//         Map<String, Object> response = new LinkedHashMap<>();
//         try {
//             Booking booking = bookingService.getBookingById(id);
//             response.put("status", "success");
//             response.put("responseCode", "00");
//             response.put("data", booking);
//             return ResponseEntity.ok(response);
//         } catch (RuntimeException e) {
//             response.put("status", "failed");
//             response.put("responseCode", "01");
//             response.put("message", e.getMessage());
//             return ResponseEntity.badRequest().body(response);
//         }
//     }

//     @PostMapping
//     public ResponseEntity<Map<String, Object>> createBooking(@RequestBody Booking booking) {
//         Map<String, Object> response = new LinkedHashMap<>();
//         try {
//             Booking newBooking = bookingService.createBooking(booking);
//             response.put("status", "success");
//             response.put("responseCode", "00");
//             response.put("data", newBooking);
//             return ResponseEntity.ok(response);
//         } catch (RuntimeException e) {
//             response.put("status", "failed");
//             response.put("responseCode", "01");
//             response.put("message", e.getMessage());
//             return ResponseEntity.badRequest().body(response);
//         }
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Map<String, Object>> deleteBooking(@PathVariable Long id) {
//         Map<String, Object> response = new LinkedHashMap<>();
//         try {
//             bookingService.deleteBooking(id);
//             response.put("status", "success");
//             response.put("responseCode", "00");
//             response.put("message", "Booking dengan ID " + id + " berhasil dihapus.");
//             return ResponseEntity.ok(response);
//         } catch (RuntimeException e) {
//             response.put("status", "failed");
//             response.put("responseCode", "01");
//             response.put("message", e.getMessage());
//             return ResponseEntity.badRequest().body(response);
//         }
//     }
// }


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

import com.busbooking.api.model.Booking;
import com.busbooking.api.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;

    // Get all booking
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("responseCode", "00");
        response.put("data", bookings);

        return ResponseEntity.ok(response);
    }

    // Get booking by id
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getBookingById(@PathVariable Long id) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Booking booking = bookingService.getBookingById(id);
            response.put("status", "success");
            response.put("responseCode", "00");
            response.put("data", booking);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("status", "failed");
            response.put("responseCode", "01");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Create booking ticket
    @PostMapping
    public ResponseEntity<Map<String, Object>> createBooking(@RequestBody Booking booking) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Booking newBooking = bookingService.createBooking(booking);
            Map<String, Object> busData = new LinkedHashMap<>();
            busData.put("id", newBooking.getBus().getId());
            busData.put("name", newBooking.getBus().getName());
            busData.put("route", newBooking.getBus().getRoute());
            // busData.put("capacity", newBooking.getBus().getCapacity());

            Map<String, Object> bookingData = new LinkedHashMap<>();
            bookingData.put("id", newBooking.getId());
            bookingData.put("bus", busData); 

            response.put("status", "success");
            response.put("responseCode", "00");
            response.put("data", bookingData);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("status", "failed");
            response.put("responseCode", "01");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Delete booking ticket
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteBooking(@PathVariable Long id) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            bookingService.deleteBooking(id);
            response.put("status", "success");
            response.put("responseCode", "00");
            response.put("message", "Booking dengan ID " + id + " berhasil dihapus.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("status", "failed");
            response.put("responseCode", "01");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}


