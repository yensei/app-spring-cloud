package py.com.yensei.store.shopping.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import py.com.yensei.store.shopping.models.Product;

@Data
@Entity
@Table(name = "tl_sho_inv_item")
public class InvoiceItem implements Serializable{ 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "El stock debe ser mayor que cero")
    private Double quantity;
    
    @Positive(message = "El precio debe ser mayor que cero")
    private Double  price;

    @Column(name = "product_id")
    private Long productId;

    @Transient
    private Double subTotal;

    public Double getSubTotal(){
        if (this.price >0  && this.quantity >0 ){
            return this.quantity * this.price;
        }else {
            return 0.0;
        }
    }
    public InvoiceItem(){
        this.quantity=0.0;
        this.price=0.0;
    }

    @Transient
    private Product product;
}
