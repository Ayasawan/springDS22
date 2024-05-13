package com.Springpro.Springpro.C2C.Controller;

import com.Springpro.Springpro.C2C.Entity.Customer;
import com.Springpro.Springpro.C2C.Entity.Product;
import com.Springpro.Springpro.C2C.Repository.CustomerRepo;
import com.Springpro.Springpro.C2C.Repository.ProductRepo;
import com.Springpro.Springpro.C2C.Service.CustomerService;
import com.Springpro.Springpro.C2C.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping(value = "customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private ProductService productService;



    private final ProductRepo productRepository;
    private final CustomerRepo customerRepository;

    public CustomerController(ProductRepo productRepository, CustomerRepo customerRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.productService = productService;
    }



   @PostMapping("/addCustomer")
   public Customer addCustomer(@RequestBody Customer customer) {
       return customerService.addDetails(customer);
   }

    @PostMapping("/registerCustomer")
    public ResponseEntity<String> register(@RequestBody Customer customer) {
        if (customerService.isEmailExists(customer.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("البريد الإلكتروني موجود بالفعل.");
        }

        customerService.saveCustomer(customer);
        return ResponseEntity.ok("تم إنشاء حساب الشركة بنجاح.");
    }

    @PostMapping("/loginCustomer")
    public ResponseEntity<String> login(@RequestBody Customer customer) {
        Customer authenticatedCustomer = customerService.authenticate(customer.getEmail(), customer.getPassword());
        if (authenticatedCustomer != null) {
            return ResponseEntity.ok("تم تسجيل الدخول بنجاح.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("فشل تسجيل الدخول.");
        }
    }


    @GetMapping("/getCustomerProducts/{customerId}")
    public ResponseEntity<List<Product>> getCustomerProducts(@PathVariable("customerId") int customerId) {
        try {
            List<Product> products = productService.getProductsByCustomerId(customerId);

            if (products.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(products);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {
        try {
            Optional<Customer> customerOptional = customerRepository.findById(customerId);
            if (!customerOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("العميل غير موجود.");
            }

            // حذف المنتجات المرتبطة بالعميل
            productRepository.deleteById(customerId);

            // حذف العميل
            customerRepository.delete(customerOptional.get());

            return ResponseEntity.ok("تم حذف العميل وجميع المنتجات المرتبطة به بنجاح.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("حدث خطأ في الخادم.");
        }
    }

}
