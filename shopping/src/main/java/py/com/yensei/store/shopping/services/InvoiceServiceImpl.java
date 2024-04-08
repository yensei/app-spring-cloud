package py.com.yensei.store.shopping.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.com.yensei.store.shopping.entities.Invoice;
import py.com.yensei.store.shopping.repositories.InvoiceRepository;
import py.com.yensei.store.utils.constants.Status;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> findInvoiceAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDb = invoiceRepository.findByNumber(invoice.getNumber());
        if(invoiceDb != null){
            return invoiceDb;
        } 

        invoice.setStatus(Status.CREATED);
        return invoiceRepository.save(invoice);
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
        return Optional.of(invoiceRepository.getReferenceById(id));
    }

}
