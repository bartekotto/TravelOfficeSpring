package com.example.traveloffice;

import java.time.LocalDate;

public abstract class Trip {
    private LocalDate start;
    private LocalDate end;
    private String destination;
    private int price;


    public Trip(LocalDate start, LocalDate end, String destination, int price) {
        this.start = start;
        this.end = end;
        this.destination = destination;
        this.price =price;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "start=" + start +
                ", end=" + end +
                ", destination='" + destination + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}
