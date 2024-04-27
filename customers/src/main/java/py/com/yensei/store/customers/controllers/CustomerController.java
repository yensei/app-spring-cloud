package py.com.yensei.store.customers.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import py.com.yensei.store.customers.entities.Customer;
import py.com.yensei.store.customers.entities.Region;
import py.com.yensei.store.customers.services.CustomerService;
import py.com.yensei.store.utils.response.ResponseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> listAllCustomers(
            @RequestParam(name = "regionId", required = false) Long regionId) {
        log.info("listAllCustormers Called: regionid:{} ",regionId);
        List<Customer> results = new ArrayList<>();
        if (regionId == null) {
            results = customerService.findCustomerAll();
            if (results.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        } else {
            Region region = new Region();
            region.setId(regionId);
            results = customerService.findCustomersByRegion(region);
            if (results.isEmpty()) {
                log.warn("Customers con regionId {} no encontrado", regionId);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok(results);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
        log.info("createCustomer Called: customer:{} ",customer);
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ResponseUtil.formatMessage(result));
        }

        Customer customerDB = customerService.createCustomer(customer);
        log.info("Customer creado con ID {}", customerDB.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDB);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        log.info("getCustomer Called: id:{} ",id);
        Optional<Customer> opt = customerService.getCustomer(id);
        if (opt.isPresent()) {
            log.info("Customer con id {} encontrado", id);
            Customer customer = opt.get();
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        log.info("updateCustomer Called: id:{} --> customer {}",id,customer);
        Optional<Customer> opt = customerService.getCustomer(id);
        if (opt.isPresent()) {
            customer.setId(id);
            Customer result = customerService.updateCustomer(customer);
            log.info("Customer con id {} modificado", id);
            return ResponseEntity.ok(result);
        }
        log.error("Modificar customer con id {} falló, cliente no encontrado", id);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) {
        log.info("deleteCustomer Called: id:{} ",id);
        Optional<Customer> opt = customerService.getCustomer(id);
        if (opt.isPresent()) {
            Customer result = customerService.deleteCustomer(id);
            log.info("Customer con id {} borrado", id);
            return ResponseEntity.ok(result);
        }
        log.error("Borrar customer con id {} falló, cliente no encontrado", id);
        return ResponseEntity.notFound().build();
    }
}
