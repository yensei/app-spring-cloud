package py.com.yensei.store.shopping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.yensei.store.shopping.entities.Invoice;
import java.util.List;


public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    public List<Invoice> findByCustomerId(Long customerId);
    public Invoice findByNumber(String number);
}
