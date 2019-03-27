package com.example.traveloffice;

import com.example.traveloffice.Trip;

import java.time.LocalDate;

public class DomesticTrip extends Trip {


    private int ownArrivalDiscount;

    public DomesticTrip(LocalDate start, LocalDate end, String destination, int price) {
        super(start, end, destination, price);
    }

    public int getOwnArrivalDiscount() {
        return ownArrivalDiscount;
    }

    public void setOwnArrivalDiscount(int ownArrivalDiscount) {
        this.ownArrivalDiscount = ownArrivalDiscount;
    }

    @Override
    public int getPrice() {
        return super.getPrice()-getOwnArrivalDiscount();
    }
}
