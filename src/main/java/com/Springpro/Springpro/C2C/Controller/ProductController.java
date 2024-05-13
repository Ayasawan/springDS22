package com.Springpro.Springpro.C2C.Controller;

import com.Springpro.Springpro.C2C.Entity.Customer;
import com.Springpro.Springpro.C2C.Entity.Product;
import com.Springpro.Springpro.C2C.Repository.CustomerRepo;
import com.Springpro.Springpro.C2C.Repository.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepo productRepository;
    private final CustomerRepo customerRepository;

    public ProductController(ProductRepo productRepository, CustomerRepo customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }


    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        try {
            Customer customer = product.getCustomer();
            if (customer != null) {
                // استعادة القيم المرتبطة بـ customer_id
                Customer retrievedCustomer = customerRepository.findById(customer.getId()).orElse(null);
                if (retrievedCustomer != null) {
                    // تعيين الأعمدة المستعادة في كائن المنتج
                product.getCustomer().setName(retrievedCustomer.getName());
                product.getCustomer().setEmail(retrievedCustomer.getEmail());
                product.getCustomer().setLocation(retrievedCustomer.getLocation());

                }
            }

            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/buyProduct/{productId}")
    public ResponseEntity<String> buyProduct(@PathVariable int productId) {
        try {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (!productOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("المنتج لم يعد متوفر .");
            }

            Product product = productOptional.get();
            double productPrice = product.getPrice(); // سعر المنتج

            productRepository.delete(product);

            return ResponseEntity.ok("تم شراء المنتج بنجاح." +
                    " سعر المنتج:                                    " + productPrice);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("حدث خطأ في الخادم.");
        }
    }

}