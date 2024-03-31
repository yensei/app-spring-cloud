package py.com.yensei.store.products.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.com.yensei.store.products.entities.Category;
import py.com.yensei.store.products.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByCategory(Category category);

}
