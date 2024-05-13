package com.Springpro.Springpro.C2C.Service;

import com.Springpro.Springpro.C2C.Entity.Customer;
import com.Springpro.Springpro.C2C.Entity.Product;
import com.Springpro.Springpro.C2C.Repository.CustomerRepo;
import com.Springpro.Springpro.C2C.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final CustomerRepo customerRepo;

    @Autowired
    public ProductService(ProductRepo productRepo, CustomerRepo customerRepo) {
        this.productRepo = productRepo;
        this.customerRepo = customerRepo;
    }

    public Product saveProduct(Product product, int customerId) {
        Customer customer = validateCustomer(customerId);
        product.setCustomer(customer);
        return productRepo.save(product);
    }

    private Customer validateCustomer(int customerId) {
        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new IllegalArgumentException("Invalid customer ID");
        }
    }

    public List<Product> getCustomerProducts(int customerId) {
        Customer customer = validateCustomer(customerId);
        return customer.getProducts();
    }

    public List<Product> getProductsByCustomerId(int customerId) {
        return productRepo.findByCustomerId(customerId);
    }


}