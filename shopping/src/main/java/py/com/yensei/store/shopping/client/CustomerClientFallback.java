package py.com.yensei.store.shopping.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import py.com.yensei.store.shopping.models.Customer;

@Component
@Slf4j
public class CustomerClientFallback implements CustomerClient {

    @Override
    public ResponseEntity<Customer> getCustomer(Long id) {
        log.warn("CustomerClientFallback.getCustomer(Long id); was called!");
        Customer customer = Customer.builder()
                            .firstname("none")
                            .lastname("none")
                            .email("none")
                            .photoUrl("none")
                            .ruc("none").build();
        return ResponseEntity.ok(customer);
    }

}
