package com.example.traveloffice;

import com.example.traveloffice.Trip;

import java.time.LocalDate;

public class AbroadTrip extends Trip {
    private int insurance;

    public AbroadTrip(LocalDate start, LocalDate end, String destination, int price) {
        super(start, end, destination, price);
    }

    public int getInsurance() {
        return insurance;
    }

    public void setInsurance(int insurance) {
        this.insurance = insurance;
    }

    @Override
    public int getPrice() {
        return super.getPrice()+getInsurance();
    }
}
