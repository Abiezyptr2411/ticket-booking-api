package com.busbooking.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "bookings", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"bus_id", "seat_number"})
})
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerName;
    private int seatCount;

    @Column(nullable = false)
    private String seatNumber;  // Tambahkan seatNumber

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    public enum Status {
        PENDING, CONFIRMED, CANCELLED
    }

    public Booking() {}

    public Booking(String passengerName, int seatCount, String seatNumber, Bus bus) {
        this.passengerName = passengerName;
        this.seatCount = seatCount;
        this.seatNumber = seatNumber;
        this.bus = bus;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }

    public int getSeatCount() { return seatCount; }
    public void setSeatCount(int seatCount) { this.seatCount = seatCount; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Bus getBus() { return bus; }
    public void setBus(Bus bus) { this.bus = bus; }
}

