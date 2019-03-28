package com.example.traveloffice;

import com.example.traveloffice.Service.TravelOfficeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest
public class TravelofficeApplicationTests {
    TravelOfficeService travelOfficeService = new TravelOfficeService();

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void teststd() {
        LocalDate ldate = LocalDate.of(1111, 2, 1);
        LocalDate date = travelOfficeService.stringToDate("1-2-1111");
        assertEquals(date, ldate);
    }
    @Test
    public void addCustomer
    @Test
    public void contextLoads() {
        LocalDate ldate = LocalDate.of(1999, 2, 1);
        System.out.println(ldate.toString());
    }

}
