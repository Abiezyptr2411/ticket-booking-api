package com.busbooking.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.busbooking.api.model.Booking;
import com.busbooking.api.model.Bus;
import com.busbooking.api.repository.BookingRepository;
import com.busbooking.api.repository.BusRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BusRepository busRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking dengan ID " + id + " tidak ditemukan."));
    }

    public Booking createBooking(Booking booking) {
        if (booking.getBus() == null || booking.getBus().getId() == null) {
            throw new RuntimeException("Bus ID harus disertakan dalam request body.");
        }

        if (booking.getSeatNumber() == null || booking.getSeatNumber().isEmpty()) {
            throw new RuntimeException("Seat number harus disertakan.");
        }

        Bus bus = busRepository.findById(booking.getBus().getId())
                .orElseThrow(() -> new RuntimeException("Bus dengan ID " + booking.getBus().getId() + " tidak ditemukan."));

        // Cek apakah kursi sudah dipesan
        Optional<Booking> existingBooking = bookingRepository.findByBusIdAndSeatNumber(bus.getId(), booking.getSeatNumber());
        if (existingBooking.isPresent()) {
            throw new RuntimeException("Kursi " + booking.getSeatNumber() + " sudah dipesan.");
        }

        booking.setBus(bus);
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking dengan ID " + id + " tidak ditemukan."));

        bookingRepository.delete(booking);
    }
}


