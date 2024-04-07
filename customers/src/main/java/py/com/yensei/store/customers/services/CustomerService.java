package py.com.yensei.store.customers.services;

import java.util.List;
import java.util.Optional;

import py.com.yensei.store.customers.entities.Customer;
import py.com.yensei.store.customers.entities.Region;

public interface CustomerService {

    public List<Customer> findCustomerAll();
    public List<Customer> findCustomersByRegion(Region region);

    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Customer customer);
    public Optional<Customer> getCustomer(Long id);
}
