package py.com.yensei.store.products.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import py.com.yensei.store.utils.response.ResponseUtil;

import jakarta.validation.Valid;

import py.com.yensei.store.products.entities.Category;
import py.com.yensei.store.products.entities.Product;
import py.com.yensei.store.products.services.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping (value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listProduct(
            @RequestParam(name="category_id", required=false) Long categoryId){
        List<Product> products = new ArrayList<>();
        if(categoryId!=null){
            products = productService.findByCategory(Category.builder().id(categoryId).build());
            
        } else {
            products = productService.listAllProduct();
        }
        
        if(products.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Optional<Product> product = productService.getProduct(id);
            
        if(product.isPresent()){
           return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ResponseUtil.formatMessage(result));
        }
        
        Product productCreated = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);

    }


    @PutMapping(value="/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        product.setId(id);
        Product updatedProduct = productService.updateProduct(product);
        if(updatedProduct== null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);

    }


    @DeleteMapping(value="/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id){
        Product deletedProduct = productService.deleteProduct(id);
        if(deletedProduct== null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(deletedProduct);

    }

    @PatchMapping(value="/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable("id") Long id, @RequestParam(name="quantity", required = true) Double quantity){
        Product product = productService.updateStock(id, quantity);

        if(product== null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
}
