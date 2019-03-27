package com.example.traveloffice.Service;

import com.example.traveloffice.Address;
import com.example.traveloffice.Customer;
import com.example.traveloffice.TravelOffice;
import com.example.traveloffice.Trip;

public interface TravelOfficeServiceInterface {

    Trip addAbroadTrip(String startLocalDateString, String endLocalDateString, String destination, String priceString, String insurance);
    Trip addDomesticTrip(String startLocalDateString, String endLocalDateString, String destination, String priceString, String discount);
    Trip removeTrip(String id);
    Trip showTrips();
    Customer addCustomer(String street, String zip, String city, String name);
    Customer removeCustomer(String name);
    Customer showCustomers();
    Address addAddress();
    Void assignTrip();
}
