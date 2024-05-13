package com.Springpro.Springpro.B2B.Entity;

import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "Company_DB")
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Reproducer> reproducers;



}