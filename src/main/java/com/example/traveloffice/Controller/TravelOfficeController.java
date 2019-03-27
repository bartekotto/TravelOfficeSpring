package com.example.traveloffice.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import com.example.traveloffice.*;
import com.example.traveloffice.Service.TravelOfficeService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
public class TravelOfficeController {
    TravelOfficeService travelOfficeService = new TravelOfficeService();

    @PostMapping("/addaddress")
    public Address addAddress(@RequestParam String street, @RequestParam String zip, @RequestParam String city) {
        return new Address(street, zip, city);
    }

    @PostMapping("/addcustomer")
    public Customer addCustomer(@RequestParam String street, @RequestParam String zip, @RequestParam String city, @RequestParam String name) {
        return travelOfficeService.addCustomer(street, zip, city, name);
    }

    @PostMapping("/addtrip")
    public Trip addTrip(@RequestParam String tripType, @RequestParam String startLocalDateString, @RequestParam String endLocalDateString,
                        @RequestParam String destination, @RequestParam String priceString, @RequestParam String insuranceOrDiscount) {

        switch (tripType) {
            case "abroad":
                return travelOfficeService.addAbroadTrip(startLocalDateString, endLocalDateString, destination, priceString, insuranceOrDiscount);

            case "domestic":
                return travelOfficeService.addDomesticTrip(startLocalDateString, endLocalDateString, destination, priceString, insuranceOrDiscount);
            default:
                return null;
        }}
    @GetMapping("/assigntrip")
    public void assignTrip() {
        System.out.println("which customer would you like to assign the trip " +
                "to and choose the id of the trip you would like to assign");
        try {
            travelOffice.findCustomerByName(getAnswerFromUser()).setTrip(travelOffice.findTripByID(String.valueOf(getAnswerFromUser())));
        } catch (NoSuchCustomerException | NoSuchTripException nS) {
            System.out.println(nS.getMessage());
        }
    }

    @GetMapping("/removecustomer")
    public Customer removeCustomer(@RequestParam String name) {
        return travelOfficeService.removeCustomer(name);
    }

    @GetMapping("/removetrip")
    public boolean removeTrip() {
        System.out.println("which trip would you like to remove");
        try {
            return travelOffice.removeTrip(getAnswerFromUser());
        } catch (NoSuchTripException nST) {
            System.out.println(nST.getMessage());

            return false;
        }
    }

    @GetMapping("/showtrips")
    public void showTrips() {

    }

    @GetMapping("showcustomers")
    public HashSet<Customer> showCustomers() {
        return TravelOffice.getCustomerHashSet();
    }



}



