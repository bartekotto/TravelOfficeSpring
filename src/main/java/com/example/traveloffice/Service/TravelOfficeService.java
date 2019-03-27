package com.example.traveloffice.Service;

import com.example.traveloffice.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Service
public class TravelOfficeService implements TravelOfficeServiceInterface{
    private TravelOffice travelOffice = new TravelOffice();
    private int tripID = 0;


    private LocalDate stringToDate(String str) {
        Character[] breaks = {'/', '-', ' '};
        int[] date = {0, 0, 0};
        int time = 0;
        int now = 0;
        int pow = 1;
        for (int i = 0, n = str.length(); i < n; i++) {
            char c = str.charAt(i);
            if (Arrays.asList(breaks).contains(c)) {
                time++;
                pow = 1;
                now = 0;
                if (time == 2) {
                    pow = 3;
                }
            } else {
                int kek = Character.getNumericValue(c) * ((int) Math.pow(10, pow));
                now += kek;
                date[time] = now;
                pow--;
            }
        }
        return LocalDate.of(date[2], date[1], date[0]);
    }

    @Override
    public Customer addCustomer(String street, String zip, String city, String name) {
        Address address = new Address(street, zip, city);
        Customer customer = new Customer(name);
        customer.setAddress(address);
        customer.setTrip(null);
        travelOffice.addCustomer(customer);
        return customer;
    }
    @Override
    public Customer removeCustomer(String name) {
        try {
            Customer s = travelOffice.findCustomerByName(name);
            TravelOffice.getCustomerHashSet().removeIf(c -> c.equals(s));
            return s;
        } catch (NoSuchCustomerException nSC) {
            return null;
        }
    }
    @Override
    public Trip addAbroadTrip(String startLocalDateString, String endLocalDateString, String destination, String priceString, String insurance) {
        LocalDate startLocalDate = stringToDate(startLocalDateString);
        LocalDate endLocalDate = stringToDate(endLocalDateString);
        int price = Integer.parseInt(priceString);
        AbroadTrip abroadTrip = new AbroadTrip(startLocalDate, endLocalDate, destination, price);
        travelOffice.addTrip(String.valueOf(tripID), abroadTrip);
        abroadTrip.setInsurance(Integer.parseInt(insurance));
        tripID++;
        return abroadTrip;
    }
    @Override
    public Trip addDomesticTrip(String startLocalDateString, String endLocalDateString, String destination, String priceString, String discount) {
        LocalDate startLocalDate = stringToDate(startLocalDateString);
        LocalDate endLocalDate = stringToDate(endLocalDateString);
        int price = Integer.parseInt(priceString);
        DomesticTrip domesticTrip = new DomesticTrip(startLocalDate, endLocalDate, destination, price);
        travelOffice.addTrip(String.valueOf(tripID), domesticTrip);
        domesticTrip.setOwnArrivalDiscount(Integer.parseInt(discount));
        tripID++;
        return domesticTrip;
    }

    @Override
    public Trip removeTrip(String id) {
        try {
            travelOffice.removeTrip(id);
            return travelOffice.findTripByID()
        } catch (NoSuchTripException nST) {
            return null;
        }
    }

    @Override
    public Trip showTrips() {
        return null;
    }

    @Override
    public Customer showCustomers() {
        return null;
    }

    @Override
    public Address addAddress() {
        return null;
    }

    @Override
    public Void assignTrip() {
        return null;
    }
}
