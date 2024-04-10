package py.com.yensei.store.shopping.models;

import lombok.Data;
import py.com.yensei.store.utils.constants.Status;

@Data
public class Product {

    private Long id;
    private String name;
    private String description;
    private Double stock;
    private Double price;
    private Status status;
    private Category category;
}
