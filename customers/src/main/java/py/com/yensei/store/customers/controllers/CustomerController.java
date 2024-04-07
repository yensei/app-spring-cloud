package py.com.yensei.store.customers.controllers;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import py.com.yensei.store.customers.entities.Customer;
import py.com.yensei.store.customers.entities.Region;
import py.com.yensei.store.customers.services.CustomerService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    @Autowired
    CustomerService customerService;
    
    @GetMapping
    public ResponseEntity<List<Customer>> listAllCustomers(
        @RequestParam(name = "regionId", required = false) Long regionId
    ){  
        List<Customer> results = new ArrayList<>();
        if (regionId == null){
            results = customerService.findCustomerAll();
            if(results.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        } else {
            Region region = new Region();
            region.setId(regionId);
            results = customerService.findCustomersByRegion(region);
            if(results.isEmpty()){
                log.warn("Customers con regionId {} no encontrado", regionId);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok(results);
    }
}
