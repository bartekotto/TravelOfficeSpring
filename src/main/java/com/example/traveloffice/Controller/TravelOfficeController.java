package com.example.traveloffice.Controller;

import java.util.HashMap;
import java.util.HashSet;

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
        return travelOfficeService.addAddress(street, zip, city);
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
        }
    }

    @GetMapping("/assigntrip")
    public void assignTrip(@RequestParam String customerName, @RequestParam String tripId) {
        travelOfficeService.assignTrip(customerName, tripId);
    }

    @GetMapping("/removecustomer")
    public Customer removeCustomer(@RequestParam String name) {
        return travelOfficeService.removeCustomer(name);
    }

    @GetMapping("/removetrip")
    public Trip removeTrip(@RequestParam String tripId) {
        return travelOfficeService.removeTrip(tripId);
    }

    @GetMapping("/showtrips")
    public HashMap<String, Trip> showTrips() {
        return travelOfficeService.showTrips();
    }

    @GetMapping("showcustomers")
    public HashSet<Customer> showCustomers() {
        return travelOfficeService.showCustomers();
    }


}



