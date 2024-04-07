package py.com.yensei.store.products;

import java.util.Date;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import py.com.yensei.store.products.entities.Category;
import py.com.yensei.store.products.entities.Product;
import py.com.yensei.store.products.repositories.ProductRepository;
import py.com.yensei.store.products.services.ProductService;
import py.com.yensei.store.products.services.ProductServiceImpl;
import py.com.yensei.store.utils.constants.Status;


@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository repository;

    private ProductService service;

    @BeforeEach
    private void setud(){
        MockitoAnnotations.initMocks(this);
        service = new ProductServiceImpl(repository);

        Product computer = Product.builder()
            .name("computer")
            .category(Category.builder().id(1L).build())
            .description("test")
            .stock(Double.parseDouble("5"))
            .price(Double.parseDouble("12.50"))
            .status(Status.CREATED)
            .createAt(new Date())
            .build();

        Mockito.when(repository.findById(1L))
            .thenReturn(Optional.of(computer));

        // cuando se actulice que retorne el objeto actualizado
        Mockito.when(repository.save(computer)).thenReturn(computer);
    }

    @Test
    public void whenValidId_thenReturnProduct() {
        
        Product found = service.getProduct(1L).get();

        Assertions.assertThat(found.getName()).isEqualTo("computer");
    }

    @Test
    public void whenValidUpdateStock_thenReturnNewStock() {
        Product newStock = service.updateStock(1L, 2.0);
        Assertions.assertThat(newStock.getStock()).isEqualTo(7.0);
    }

}
