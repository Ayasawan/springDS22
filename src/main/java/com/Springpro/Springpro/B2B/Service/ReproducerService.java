package com.Springpro.Springpro.B2B.Service;

import com.Springpro.Springpro.B2B.Entity.Reproducer;
import com.Springpro.Springpro.B2B.Repository.ReproducerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReproducerService {

    @Autowired
    private ReproducerRepo reproducerRepo;

    public Reproducer saveReproducer(Reproducer reproducer) {
        return reproducerRepo.save(reproducer);
    }

    public void deleteReproducer(int id) {
        reproducerRepo.deleteById(id);
    }

    public Reproducer updateReproducer(Reproducer reproducer) {
        return reproducerRepo.save(reproducer);
    }

    public List<Reproducer> getAllReproducers() {
        return reproducerRepo.findAll();
    }

    public Optional<Reproducer> getReproducerById(int id) {
        return reproducerRepo.findById(id);
    }

    public boolean isReproducerExists(int reproducerId) {
        return reproducerRepo.existsById(reproducerId);
    }


    public Reproducer getReproducer(int id) {
        Optional<Reproducer> optionalReproducer = getReproducerById(id);
        return optionalReproducer.orElse(null);
    }



}