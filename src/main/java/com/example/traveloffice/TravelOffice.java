package com.example.traveloffice;

import javax.persistence.Entity;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
@Entity
public class TravelOffice {
    private static HashSet<Customer> customerHashSet = new HashSet<>();
    private static HashMap<String, Trip> tripMap = new HashMap<>();

    public void addCustomer(Customer c) {
        customerHashSet.add(c);
    }

    public int customerCount() {
        return customerHashSet.size();
    }

    String getCustomerInfo() {
        Iterator value = customerHashSet.iterator();

        StringBuilder info = new StringBuilder();
        while (value.hasNext()) {
            info.append(value.next().toString()).append("\n");
        }
        return info.toString();
    }

    public static HashMap<String, Trip> getTripMap() {
        return tripMap;
    }

    public static void setTripMap(HashMap<String, Trip> tripMap) {
        TravelOffice.tripMap = tripMap;
    }

    public static HashSet<Customer> getCustomerHashSet() {
        return customerHashSet;
    }

    public static void setCustomerHashSet(HashSet<Customer> customerHashSet) {
        TravelOffice.customerHashSet = customerHashSet;
    }

    String getTripInfo() {
        Iterator value = tripMap.entrySet().iterator();

        StringBuilder info = new StringBuilder();
        while (value.hasNext()) {
            info.append(value.next().toString()).append("\n");
        }
        return info.toString();
    }

    public void addTrip(String s, Trip t) {
        tripMap.put(s, t);
    }

    public Trip removeTrip(String s) throws NoSuchTripException {
        Trip trip = tripMap.get(s);
        if (tripMap.remove(s) != null) {
            return trip;
        } else {
            throw new NoSuchTripException("No such trip was found");
        }
    }

    public Customer findCustomerByName(String customerName) throws NoSuchCustomerException {
        for (Customer c :
                customerHashSet) {
            if (c.getName().equals(customerName)) {
                return c;
            }

        }
        throw new NoSuchCustomerException("No such customer was found!");
    }

    public Trip findTripByID(String tripID) throws NoSuchTripException {
        if (tripMap.containsKey(tripID)) {
            return tripMap.get(tripID);

        }
        throw new NoSuchTripException("No such trip was found!");

    }

    boolean removeCustomer(Customer c) {
        return customerHashSet.remove(c);
    }

    public static void main(String[] args) {


    }
}
