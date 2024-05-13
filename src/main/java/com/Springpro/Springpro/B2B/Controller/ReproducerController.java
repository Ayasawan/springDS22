package com.Springpro.Springpro.B2B.Controller;

import com.Springpro.Springpro.B2B.Entity.Company;
import com.Springpro.Springpro.B2B.Entity.Reproducer;
import com.Springpro.Springpro.B2B.Service.CompanyService;
import com.Springpro.Springpro.B2B.Service.ReproducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class ReproducerController {

    @Autowired
    private ReproducerService reproducerService;

    private CompanyService companyService;

    @PostMapping("/addReproducer")
    public Reproducer addReproducer(@RequestBody Reproducer reproducer, Principal principal) {
        String loggedInCompany = null;
        if (principal != null) {
            loggedInCompany = principal.getName();
        }

        if (loggedInCompany != null) {
            Company company = companyService.getCompanyByName(loggedInCompany);
            if (company != null) {
                reproducer.setCompany(company);
            }
        }

        return reproducerService.saveReproducer(reproducer);
    }


    @PostMapping("/deleteReproducer")
    public ResponseEntity<String> deleteReproducer(@RequestBody Reproducer reproducer) {
        if (!reproducerService.isReproducerExists(reproducer.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("المنتج غير موجود.");
        }

        reproducerService.deleteReproducer(reproducer.getId());
        return ResponseEntity.ok("تم حذف المنتج بنجاح.");
    }


    @PostMapping("/updateReproducer")
    public ResponseEntity<String> updateReproducer(@RequestBody Reproducer reproducer) {
        if (!reproducerService.isReproducerExists(reproducer.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("المنتج غير موجود.");
        }

        Reproducer updatedReproducer = reproducerService.updateReproducer(reproducer);
        if (updatedReproducer != null) {
            return ResponseEntity.ok("تم تحديث معلومات المنتج بنجاح.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("حدث خطأ أثناء تحديث معلومات المنتج.");
        }
    }

    @GetMapping("/getAllReproducers")
    public List<Reproducer> getAllReproducers() {
        return reproducerService.getAllReproducers();
    }

    @GetMapping("/getReproducerById/{id}")
    public ResponseEntity<Reproducer> getReproducerById(@PathVariable int id) {
        Optional<Reproducer> reproducer = reproducerService.getReproducerById(id);
        if (reproducer.isPresent()) {
            return ResponseEntity.ok(reproducer.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}