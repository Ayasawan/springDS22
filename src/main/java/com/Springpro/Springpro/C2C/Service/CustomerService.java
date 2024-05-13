package com.Springpro.Springpro.C2C.Service;

import com.Springpro.Springpro.B2B.Entity.Company;
import com.Springpro.Springpro.C2C.Entity.Customer;
import com.Springpro.Springpro.C2C.Entity.Product;
import com.Springpro.Springpro.C2C.Repository.CustomerRepo;
import com.Springpro.Springpro.C2C.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    private ProductRepo productRepo;


    public Customer addDetails(Customer customer) {
        return customerRepo.save(customer);
    }

    public void saveCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    public boolean isEmailExists(String email) {
        return customerRepo.existsByEmail(email);
    }

    public Customer authenticate(String email, String password) {
        return customerRepo.findByEmailAndPassword(email, password);
    }

    public boolean isCustomerExists(int customerId) {
        return customerRepo.existsById(customerId);
    }

// ...

    @Transactional
    public void deleteCustomerAndProducts(int customerId) {
        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            // حذف جميع المنتجات المرتبطة بالعميل
            List<Product> products = customer.getProducts();
            productRepo.deleteInBatch(products);

            // حذف العميل
            customerRepo.deleteById(customerId);
        } else {
            throw new IllegalArgumentException("Invalid customer ID");
        }
    }



}