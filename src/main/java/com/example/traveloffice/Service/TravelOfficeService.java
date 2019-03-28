package com.example.traveloffice.Service;

import com.example.traveloffice.*;
import org.aspectj.weaver.ast.Test;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

@Service
public class TravelOfficeService implements TravelOfficeServiceInterface {
    private TravelOffice travelOffice = new TravelOffice();
    private int tripID = 0;


    public LocalDate stringToDate(String dateString) {
        Character[] breaksList = {'/', '-', ' ','.'};
        int[] date = {0, 0, 0};
        String timeValue = "";
        int dayMonthYear = 0;
        dateString +=".";
        for (int i = 0, n = dateString.length(); i < n; i++) {
            char c = dateString.charAt(i);
            if (Arrays.asList(breaksList).contains(c)) {
                date[dayMonthYear] = Integer.valueOf(timeValue);
                dayMonthYear++;
                timeValue = "";
            } else {
                timeValue += c;
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
            for (Customer customer :
                showCustomers()) {
            if (customer.getTrip() == travelOffice.findTripByID(String.valueOf(id))){
                customer.setTrip(null);
            }
        }
            return travelOffice.removeTrip(id);
        } catch (NoSuchTripException nST) {
            return null;
        }
    }

    @Override
    public HashMap<String, Trip> showTrips() {
        return TravelOffice.getTripMap();
    }

    @Override
    public HashSet<Customer> showCustomers() {
        return TravelOffice.getCustomerHashSet();
    }

    @Override
    public Address addAddress(String street, String zip, String city) {
        return new Address(street, zip, city);
    }

    @Override
    public void assignTrip(String name, String id) {
        try {
            travelOffice.findCustomerByName(name).setTrip(travelOffice.findTripByID(String.valueOf(id)));
        } catch (NoSuchCustomerException | NoSuchTripException nS) {
            nS.printStackTrace();
        }
    }
}
