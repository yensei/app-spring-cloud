package py.com.yensei.store.shopping.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.com.yensei.store.shopping.client.CustomerClient;
import py.com.yensei.store.shopping.client.ProductClient;
import py.com.yensei.store.shopping.entities.Invoice;
import py.com.yensei.store.shopping.entities.InvoiceItem;
import py.com.yensei.store.shopping.models.Customer;
import py.com.yensei.store.shopping.models.Product;
import py.com.yensei.store.shopping.repositories.InvoiceRepository;
import py.com.yensei.store.utils.constants.Status;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    CustomerClient customerClient;

    @Autowired
    ProductClient productClient;

    @Override
    public List<Invoice> findInvoiceAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDb = invoiceRepository.findByNumber(invoice.getNumber());
        if(invoiceDb == null){
            invoice.setStatus(Status.CREATED);
            invoiceDb = invoiceRepository.save(invoice);
            // actualizamos el stock llamando al MCS
            invoiceDb.getItems().forEach(invoiceItem -> {
                productClient.updateStockProduct(invoiceItem.getProductId(), invoiceItem.getQuantity()*-1);
            });

        } 


        return invoiceDb;
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Optional<Invoice> target = getInvoice(invoice.getId());
        if (target.isPresent()){
            Invoice invoiceDB = target.get();
            invoiceDB.setCustomerId(invoice.getCustomerId());
            invoiceDB.setDescription(invoice.getDescription());
            invoiceDB.setNumber(invoice.getNumber());
            invoiceDB.getItems().clear();
            invoiceDB.setItems(invoice.getItems());
            return invoiceRepository.save(invoiceDB);
        }
        return null;
    }

    @Override
    public Invoice deleteInvoice(Long invoiceId) {
        Optional<Invoice> target = getInvoice(invoiceId);
        if (target.isPresent()){
            Invoice invoiceDB = target.get();
            invoiceDB.setStatus(Status.DELETED);
            return invoiceRepository.save(invoiceDB);
        }
        return null;
    }

    @Override
    public  Optional<Invoice> getInvoice(Long invoiceId) {
        Long id = invoiceId == null ? 0L : invoiceId; // si null , seteamos 0
        Optional<Invoice> result = Optional.of(invoiceRepository.getReferenceById(id));
        if(result.isPresent()){
            Invoice invoice = result.get();
            Customer customer = customerClient.getCustomer(invoice.getCustomerId()).getBody();
            invoice.setCustomer(customer);

            List<InvoiceItem> listItems = invoice.getItems().stream().map(invoiceItem -> {
                Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
                invoiceItem.setProduct(product);
                return invoiceItem;
            }).collect(Collectors.toList());
            invoice.setItems(listItems);
        }
        return result;
    }

}
