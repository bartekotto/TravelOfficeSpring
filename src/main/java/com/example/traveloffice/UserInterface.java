package com.example.traveloffice;

public interface UserInterface {
    Customer addCustomer();

    Trip addTrip();

    void assignTrip();

    boolean removeCustomer();

    boolean removeTrip();

    void showTrips();

    void showCustomers();
}
