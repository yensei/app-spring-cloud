package py.com.yensei.store.products;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import py.com.yensei.store.products.entities.Category;
import py.com.yensei.store.products.entities.Product;
import py.com.yensei.store.products.repositories.ProductRepository;
import py.com.yensei.store.utils.constants.Status;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void whenFindBycategory_thenReturnProductList() {
        Product product = Product.builder()
            .name("computer")
            .category(Category.builder().id(1L).build())
            .description("")
            .stock(Double.parseDouble("10"))
            .price(Double.parseDouble("1240.20"))
            .status(Status.CREATED)
            .createAt(new Date())
            .build();
        productRepository.save(product);
        List<Product> founds = productRepository.findByCategory(product.getCategory());
        Assertions.assertThat(founds.size()).isEqualTo(3); 
    }   

}
