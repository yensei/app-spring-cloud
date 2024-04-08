package py.com.yensei.store.shopping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.yensei.store.shopping.entities.InvoiceItem;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long>{

}
