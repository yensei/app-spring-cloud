package py.com.yensei.store.shopping.services;

import java.util.List;
import java.util.Optional;

import py.com.yensei.store.shopping.entities.Invoice;

public interface InvoiceService {
    public List<Invoice> findInvoiceAll();

    public Invoice createInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice deleteInvoice(Long invoiceId);

    public Optional<Invoice> getInvoice(Long id);

}
