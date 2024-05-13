package com.Springpro.Springpro.B2B.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "Reproducer_DB")
@NoArgsConstructor
@AllArgsConstructor
public class Reproducer {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;


    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

//    @OneToMany(mappedBy = "reproducer", cascade = CascadeType.ALL)
//    private List<ProdOrdd> prodOrds;
}