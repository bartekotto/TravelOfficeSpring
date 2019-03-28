package com.example.traveloffice;

import com.example.traveloffice.Service.TravelOfficeService;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TravelofficeApplicationTests {
    TravelOfficeService travelOfficeService = new TravelOfficeService();
    TravelOffice travelOffice = new TravelOffice();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void teststd() {
        LocalDate ldate = LocalDate.of(1111, 2, 1);
        LocalDate date = travelOfficeService.stringToDate("1-2-1111");
        assertEquals(date, ldate);
    }

    @Test
    public void addCustomer() throws Exception {
        JSONObject customer = new JSONObject();

        JSONObject address = new JSONObject();
        address.put("street", "szkolna");
        address.put("zip", "12345");
        address.put("city", "la");

        customer.put("name", "janusz");
        customer.put("trip", null);

        customer.put("address", address);

        this.mockMvc.perform(post("/addcustomer?street=szkolna&zip=12345&city=la&name=janusz")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customer.toString())).andExpect(status().isOk())
                .andExpect(content().json(customer.toString()));
    }

    @Test
    public void addTrip() throws Exception {
        JSONObject trip = new JSONObject();
        trip.put("start", "1999-01-01");
        trip.put("end", "1999-02-01");
        trip.put("destination", "lv");
        trip.put("price", "1000");
        trip.put("insuranceOrDiscount", "100");
        JSONObject calculatedTrip = new JSONObject();
        calculatedTrip = trip;
        calculatedTrip.remove("insuranceOrDiscount");
        calculatedTrip.put("price", 900);
        this.mockMvc.perform(post("/addtrip?tripType=domestic&startLocalDateString=01-01-1999&endLocalDateString=01-02-1999&destination=lv&priceString=1000&insuranceOrDiscount=100")
                .contentType(MediaType.APPLICATION_JSON)
                .content(trip.toString())).andExpect(status().isOk())
                .andExpect(content().json(calculatedTrip.toString()));

    }
    @Test
    public void assignTrip() throws Exception{
        this.mockMvc.perform(post("/addcustomer?street=szkolna&zip=12345&city=la&name=janusz"));
        this.mockMvc.perform(post("/addtrip?tripType=domestic&startLocalDateString=01-01-1999&endLocalDateString=01-02-1999&destination=lv&priceString=1000&insuranceOrDiscount=100"));
        this.mockMvc.perform(get("/assigntrip?custome2rName=janusz&tripId=0"));
        assertSame(travelOffice.findTripByID("0"), travelOffice.findCustomerByName("janusz").getTrip());


    }
    @Test
    public void contextLoads() {
        LocalDate ldate = LocalDate.of(1999, 2, 1);
        System.out.println(ldate.toString());
    }

}
