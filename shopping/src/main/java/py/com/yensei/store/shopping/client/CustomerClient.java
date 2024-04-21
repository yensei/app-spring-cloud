package py.com.yensei.store.shopping.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import py.com.yensei.store.shopping.models.Customer;

@FeignClient(name = "customers", url = "http://localhost:8092/customers", fallback = CustomerClientFallback.class)
public interface CustomerClient {


    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id);
}
