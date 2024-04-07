package py.com.yensei.store.customers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.yensei.store.customers.entities.Customer;
import py.com.yensei.store.customers.entities.Region;

import java.util.List;
import java.util.Optional;


public interface CustormerRespository extends JpaRepository<Customer,Long> {

    public Optional<Customer> findByDocumentId(String documentId);
    public List<Customer> findByLastname(String lastname);
    public List<Customer> findByRegion(Region region);

}
