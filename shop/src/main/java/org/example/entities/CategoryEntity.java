//package org.example.entities;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Data
//@Entity
//@Table(name="tbl_Categories")
//public class CategoryEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(length = 200, nullable = false)
//    private String name;
//
//    @Column(length = 200)
//    private String image;
//
//    @OneToMany(mappedBy = "category")
//    private List<ProductEntity> products;
//}
