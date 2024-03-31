package py.com.yensei.store.products.services;

import java.util.List;
import java.util.Optional;

import py.com.yensei.store.products.entities.Category;
import py.com.yensei.store.products.entities.Product;

public interface ProductService {
    List<Product> listAllProduct();
    Optional<Product> getProduct(Long id);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProduct(Long id);
    List<Product> findByCategory(Category category);
    Product updateStock(Long id, Double quantity);
}