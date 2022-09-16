package org.example.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="tbl_roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 200, nullable=false)
    private String name;

    @ManyToMany(mappedBy="roles")
    private List<UserEntity> users;

    public RoleEntity() {
        users=new ArrayList<UserEntity>();
    }
    public RoleEntity(String name) {
        this.name = name;
        users=new ArrayList<UserEntity>();
    }
}
