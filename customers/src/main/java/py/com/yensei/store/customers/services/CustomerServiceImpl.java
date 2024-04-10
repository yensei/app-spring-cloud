package py.com.yensei.store.customers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.com.yensei.store.customers.entities.Customer;
import py.com.yensei.store.customers.entities.Region;
import py.com.yensei.store.customers.repositories.CustormerRespository;
import py.com.yensei.store.utils.constants.Status;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustormerRespository custormerRespository;

    @Override
    public List<Customer> findCustomerAll() {
        return custormerRespository.findAll();
    }

    @Override
    public List<Customer> findCustomersByRegion(Region region) {
        return custormerRespository.findByRegion(region);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        // Validamos si ya existe el cliente
        Optional<Customer> cus = custormerRespository.findByDocumentId(customer.getDocumentId());
        if (cus.isPresent()) {
            return cus.get();
        }

        // si no existe, insertamos el nuevo cliente
        customer.setStatus(Status.CREATED);
        return custormerRespository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Optional<Customer> target = getCustomer(customer.getId());
        if (target.isPresent()) {
            Customer targetCustomer = target.get();
            targetCustomer.setFirstname(customer.getFirstname());
            targetCustomer.setLastname(customer.getLastname());
            targetCustomer.setPhotoUrl(customer.getPhotoUrl());
            targetCustomer.setEmail(customer.getEmail());
            targetCustomer.setRegion(customer.getRegion());
            targetCustomer.setRuc(customer.getRuc());
            return custormerRespository.save(targetCustomer);
        }
        return null;
    }

    @Override
    public Customer deleteCustomer(Long customerId) {
        Optional<Customer> target = getCustomer(customerId);
        if(target.isPresent()){
            Customer customer = target.get();
            customer.setStatus(Status.DELETED);
            return custormerRespository.save(customer);
        }
        return null;
    }

    @Override
    public Optional<Customer> getCustomer(Long customerId) {
        // Vemos si existe para modificar
        Long id = customerId == null ? 0L : customerId; // para evitar exception por NULL
        return custormerRespository.findById(id);
    }

}
