package py.com.yensei.store.products.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import py.com.yensei.store.products.entities.Category;
import py.com.yensei.store.products.entities.Product;
import py.com.yensei.store.products.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    
    private final ProductRepository repository;

    @Override
    public List<Product> listAllProduct() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return repository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus(Status.CREATED);
        product.setCreateAt(new Date());
        return repository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> optp =  getProduct(product.getId());
        if(optp.isPresent()){
            Product p = optp.get();
            p.setName(product.getName());
            p.setCategory(product.getCategory());
            p.setDescription(product.getDescription());
            p.setPrice(product.getPrice());
            p.setStock(product.getStock());
            return repository.save(p);
        }
        return null;

    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<Product> optp =  getProduct(id);
        if(optp.isPresent()){
            Product p = optp.get();
            p.setStatus(Status.DELETED);
            return repository.save(p);
        }
        return null;
            
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return repository.findByCategory(category);
    }
    /**
     * Para agregar Stock pasar valores positivos, para decrementar pasar valor negativos
     */
    @Override
    public Product updateStock(Long id, Double addQuantity) {
        Optional<Product> optp =  getProduct(id);
        if(optp.isPresent()){
            Product p = optp.get();
            p.setStock(p.getStock() + addQuantity);
            return repository.save(p);
        }
        return null;
    }

}
