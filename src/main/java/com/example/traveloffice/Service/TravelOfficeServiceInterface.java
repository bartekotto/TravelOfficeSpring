package com.example.traveloffice.Service;

import com.example.traveloffice.Address;
import com.example.traveloffice.Customer;
import com.example.traveloffice.Trip;

import java.util.HashMap;
import java.util.HashSet;

public interface TravelOfficeServiceInterface {

    Trip addAbroadTrip(String startLocalDateString, String endLocalDateString, String destination, String priceString, String insurance);
    Trip addDomesticTrip(String startLocalDateString, String endLocalDateString, String destination, String priceString, String discount);
    Trip removeTrip(String id);
    HashMap<String, Trip> showTrips();
    Customer addCustomer(String street, String zip, String city, String name);
    Customer removeCustomer(String name);
    HashSet<Customer> showCustomers();
    Address addAddress(String street, String zip, String city);
    void assignTrip(String name, String id);
}
