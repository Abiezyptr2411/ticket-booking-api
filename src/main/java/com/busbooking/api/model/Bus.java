package com.busbooking.api.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "buses")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String route;
    private int capacity;
    private int available_seats;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    // Constructors
    public Bus() {}

    public Bus(String name, String route, int capacity, int available_seats) {
        this.name = name;
        this.route = route;
        this.capacity = capacity;
        this.available_seats = available_seats;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRoute() { return route; }
    public void setRoute(String route) { this.route = route; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public int getAvailable() { return available_seats; }
    public void getAvailable(int available_seats) { this.available_seats = available_seats; }
}
