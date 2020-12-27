package com.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "E2C_SLR")
@Data
public class Seller {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String location;

    @JsonIgnoreProperties("seller")
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private Set<Product> products;

}
